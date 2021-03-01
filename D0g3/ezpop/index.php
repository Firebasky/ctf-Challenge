<?php
error_reporting(0);
highlight_file(__FILE__);
class This{
    protected $formatters;
    public function want(){
        echo "do you want to join D0g3!";
    }
    public function __call($method, $attributes)
    {
        return $this->format($method, $attributes);
    }
    public function format($formatter, $arguments)
    {
        $this->getFormatter($formatter)->patch($arguments[0][1][0]);
    }
    public function getFormatter($formatter)
    {
        if (isset($this->formatters[$formatter])) {
            return $this->formatters[$formatter];
        }
    }
}
class D0g3{
    protected $events;
    protected $event;
    public function __destruct()
    {
        $this->events->dispatch($this->event);
    }
    public function welcome(){
        echo "welcome CTFer!";
    }
}
class Ctf{
    protected $ban;
    protected $cmd;
    public function upup(){
        echo "upupupupupupup!";
    }
    public function __construct()
    {
        $this->ban='script';
        $this->cmd='whoami';
    }
    public function dispatch($cmd){
        call_user_func_array("script",$cmd);
    }
}
class Gema{
    protected $xbx;
    public function gema(){
        echo "wish you like this ezpop!";
    }
    public function __construct()
    {
        $this->xbx='script';
    }
    public function patch($Fire){
        call_user_func($this->xbx,$Fire);
    }
}
if($_POST['a']!=$_POST['b'] && md5($_POST['a'])===md5($_POST['b'])){
    if(file_get_contents(substr($_POST['a'],0,20))!=null){
        @unserialize(base64_decode($_POST['data']));
    }else{
        die('to read this file??');
    }
}else{
    die('maybe you are right??');
}
?>

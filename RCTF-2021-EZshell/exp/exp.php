<?php
// 要加密的字符串
$data = file_get_contents("Payload.class");
// 密钥
$key = 'e45e329feb5d925b';
// 加密数据 'AES-128-ECB' 可以通过openssl_get_cipher_methods()获取
$encrypt = openssl_encrypt($data, 'AES-128-ECB', $key, 0);
echo base64_encode(($encrypt));
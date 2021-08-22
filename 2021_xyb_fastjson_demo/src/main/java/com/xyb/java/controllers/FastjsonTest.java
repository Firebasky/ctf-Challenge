 package com.xyb.java.controllers;
 
 import com.alibaba.fastjson.JSON;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
 
 
 @RestController
 @RequestMapping({"/"})
 public class FastjsonTest
 {
   public String[] blacklist = { "JdbcRowSetImpl", "TemplatesImpl" };
   
   public boolean waf(String payload) {
       if (payload.contains("\\x"))
       return true;
     if (payload.contains("\\u"))
      return true;
     for (String key : this.blacklist) {
       if (payload.contains(key))
       return true;
     }
    return false;
   }
   
   @RequestMapping({"/admin/test"})
   public String test(@RequestBody String json)
   {
     if (waf(json)) return "Oh,you can't do that";
    if (json.length() < 20000) return "Oh,you are too short";//可以绕过
    JSON.parse(json);
     return "test over";
   }
 }

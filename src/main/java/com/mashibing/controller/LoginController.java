package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {}, allowedHeaders = "*", allowCredentials = "true")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("test");
//        System.out.println(username+"--"+password);
        TblUserRecord tblUserRecord = loginService.login(username, password);
        System.out.println(tblUserRecord);
        return JSONObject.toJSONString(tblUserRecord);
    }
}

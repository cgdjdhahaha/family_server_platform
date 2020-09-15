package com.mashibing.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {}, allowedHeaders = "*", allowCredentials = "true")
public class LoginController {

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("test");
        System.out.println(username+"--"+password);
        return "success";
    }
}

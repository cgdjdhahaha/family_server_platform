package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.returnJson.Permission;
import com.mashibing.returnJson.Permissions;
import com.mashibing.returnJson.ReturnObject;
import com.mashibing.returnJson.UserInfo;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {}, allowedHeaders = "*", allowCredentials = "true")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        System.out.println("test");
//        System.out.println(username+"--"+password);
        TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        ReturnObject returnObject = new ReturnObject(tblUserRecord);
        session.setAttribute("userRecord", tblUserRecord);
//        System.out.println(tblUserRecord);
        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/user/info")
    public String getInfo(HttpSession session){
//        System.out.println("SessionGet:" + session.getAttribute("userRecord"));
        TblUserRecord userRecord = (TblUserRecord) session.getAttribute("userRecord");
        String[] split = userRecord.getTblRole().getRolePrivileges().split("-");
        List<Permission> permissionList = new ArrayList<>();
        for (String s : split) {
            permissionList.add(new Permission(s));
        }
        Permissions permissions = new Permissions(permissionList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(), permissions);
        System.out.println(userInfo);
        return JSONObject.toJSONString(new ReturnObject(userInfo));
    }

    @RequestMapping("/auth/logout")
    public void logOut(HttpSession session){
        System.out.println("logout");
        session.invalidate();
    }
}

package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.TblCompany;
import com.mashibing.returnJson.ReturnObject;
import com.mashibing.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {}, allowedHeaders = "*", allowCredentials = "true")
public class EstateController {
    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public String selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.getCompany();
        return JSONObject.toJSONString(new ReturnObject(companies));
    }

    @RequestMapping("/estate/insertEstate")
    public String insertEstate(FcEstate fcEstate){
        System.out.println("insertEstate");
        Integer result = estateService.insertEstate(fcEstate);
        if (result == 0){
            return JSONObject.toJSONString(new ReturnObject("0", "插入失败"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("1", "插入成功"));
        }
    }
}

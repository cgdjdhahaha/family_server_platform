package com.mashibing.service;

import com.mashibing.bean.TblUserRecord;
import com.mashibing.mapper.TblUserRecordMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    public TblUserRecord login(String username, @Param("password") String password){
        return tblUserRecordMapper.login(username, password);
    }
}

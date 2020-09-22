package com.mashibing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.FcBuilding;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.TblCompany;
import com.mashibing.mapper.FcBuildingMapper;
import com.mashibing.mapper.FcEstateMapper;
import com.mashibing.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;
    @Autowired
    private FcEstateMapper fcEstateMapper;
    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    public List<TblCompany> getCompany(){
        List<TblCompany> company = tblCompanyMapper.getCompany();
        return company;
    }


    public Integer insertEstate(FcEstate fcEstate) {
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fe = fcEstateMapper.selectOne(queryWrapper);
        int insert = 0;
        if (fe == null){
            insert = fcEstateMapper.insert(fcEstate);
        }
        return insert;
    }

    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode("B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    public Integer updateBuilding(FcBuilding fcBuilding){
        return fcBuildingMapper.updateById(fcBuilding);
    }
}

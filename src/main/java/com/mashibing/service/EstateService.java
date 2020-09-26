package com.mashibing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.*;
import com.mashibing.mapper.*;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private FcUnitMapper fcUnitMapper;
    @Autowired
    private FcCellMapper fcCellMapper;

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
            fcBuilding.setBuildingCode(estateCode + "B" + (i + 1));
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

    public List<FcUnit> selectUnit(UnitMessage unitMessage){
        List<FcUnit> fcUnits = new ArrayList<>();
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setUnitCode(unitMessage.getBuildingCode() + "U" + (i + 1));
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    public Integer updateUnit(FcUnit fcUnit){
        return fcUnitMapper.updateById(fcUnit);
    }

    public List<FcCell> insertCell(CellMessage[] cellMessages){
        List<FcCell> fcCells = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {
            for (int i = 1; i < cellMessage.getStopFloor(); i++) {
                for (int j = cellMessage.getStartCellId(); j < cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setCellName(i + "0" + j);
                    fcCell.setCellCode(cellMessage.getUnitCode() + "C" + i + "0" + j);
                    fcCell.setFloorNumber(i);
                    fcCellMapper.insert(fcCell);
                    fcCells.add(fcCell);
                }
            }
        }
        return fcCells;
    }

    public List<FcBuilding> selectBuildingByEstate(String estateCode){
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        queryWrapper.select("building_name","building_code");
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        return fcBuildings;
    }

    public List<FcUnit> selectUnitByBuildingCode(String buildingCode){
        QueryWrapper<FcUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_code",buildingCode);
        queryWrapper.select("unit_name","unit_code");
        List<FcUnit> fcUnits = fcUnitMapper.selectList(queryWrapper);
        return fcUnits;
    }

    public List<FcCell> selectCellByUnitCode(String unitCode){
        QueryWrapper<FcCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit_code",unitCode);
        List<FcCell> fcCells = fcCellMapper.selectList(queryWrapper);
        return fcCells;
    }

    public List<FcEstate> selectEstate(String company){
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company", company);
        List<FcEstate> fcEstates = fcEstateMapper.selectList(queryWrapper);
        return fcEstates;
    }
}

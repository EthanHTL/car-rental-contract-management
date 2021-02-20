package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.mapper.VehicleTypeMapper;
import com.example.carrentalcontract.sercive.VehicleService;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import javax.persistence.Transient;
import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleTypeService")
public class VehicleTypeServiceImpl extends DbServiceImpl<VehicleType> implements VehicleTypeService {


    @Autowired
    private VehicleService vehicleService;
    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private VehicleTypeMapper vehicleTypeMapper;

    @Override
    public Result create(VehicleType type) {
        return super.insert(type);
    }

    @Override
    public Result update(VehicleType type) {
        return super.update(type);
    }

    @Override
    @Transient
    public Result delete(VehicleType type) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleTypeId(type.getId());
        vehicleService.deleteByType(vehicle);
        return super.destroy(type);
    }

    @Override
    public Result<PageInfo<VehicleType>> findTypePage(VehicleType type) {
        List<VehicleType> vehicleList = vehicleTypeMapper.findPage(type.getPageNum(),type.getPageSize(),type);
        PageInfo<VehicleType> info = new PageInfo<>(vehicleList);
        return Result.success(info);

    }


}
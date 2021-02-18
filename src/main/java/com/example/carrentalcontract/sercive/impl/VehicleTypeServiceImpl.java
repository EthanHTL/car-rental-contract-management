package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.mapper.VehicleTypeMapper;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleTypeService")
public class VehicleTypeServiceImpl extends DbServiceImpl<VehicleType> implements VehicleTypeService {

    @Override
    public Result create(VehicleType type) {
        return null;
    }

    @Override
    public Result update(@NonNull VehicleType type) {
        return super.update(type);
    }

    @Override
    public Result delete(@NonNull VehicleType type) {
        return super.delete(type);
    }

    @Override
    public Result<PageInfo<VehicleType>> findTypePage(VehicleType type) {
        return null;
    }

    @Override
    public Result<PageInfo<Vehicle>> findCarPageByType(Vehicle vehicle) {
        return null;
    }
}
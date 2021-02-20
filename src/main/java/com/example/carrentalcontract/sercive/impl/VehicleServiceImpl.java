package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.sercive.VehicleService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆信息表(Vehicle)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleService")
public class VehicleServiceImpl extends DbServiceImpl<Vehicle> implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Result<PageInfo<Vehicle>> findCarPageByType(Vehicle vehicle) {
        Integer pageNum = (vehicle.getPageNum()-1)* vehicle.getPageSize();
        Integer pageSize = vehicle.getPageSize();

        List<Vehicle> vehicleList = vehicleMapper.findCarPageByType(pageNum, pageSize,vehicle);
        PageInfo info = new PageInfo(vehicleList);
        return Result.success(info);
    }

    @Override
    public Result create(Vehicle vehicle) {
        return super.insert(vehicle);
    }

    @Override
    public Result update( Vehicle vehicle) {
        return super.update(vehicle);
    }

    @Override
    public Result delete( Vehicle vehicle) {
        return super.destroy(vehicle);
    }

    @Override
    public Result deleteByType(Vehicle type) {
        return Result.success(vehicleMapper.deleteByType(type));
    }
}
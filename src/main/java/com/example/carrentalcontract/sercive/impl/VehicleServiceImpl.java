package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.sercive.VehicleService;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private VehicleTypeService typeService;
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Result<PageInfo<Vehicle>> findCarPageByType(Vehicle vehicle) {
        // Integer pageNum = (vehicle.getPageNum()-1)* vehicle.getPageSize();
        Integer pageNum = vehicle.getPageNum();
        Integer pageSize = vehicle.getPageSize();

        List<Vehicle> vehicleList = vehicleMapper.findCarPageByType(pageNum, pageSize, vehicle);
        PageInfo info = new PageInfo(vehicleList);
        return Result.success(info);
    }

    @Override
    @Transactional
    public Result create(Vehicle vehicle) {
        Vehicle data = super.insert(vehicle).getData();
        VehicleType vehicleType = typeService.selectByPrimaryKey(data.getVehicleTypeId()).getData();
        vehicleType.setInventory(vehicleType.getInventory() + 1);
        return typeService.update(vehicleType);
    }

    @Override
    public Result update(Vehicle vehicle) {
        return super.update(vehicle);
    }

    @Override
    public Result delete(Vehicle vehicle) {
        return super.destroy(vehicle);
    }

    @Override
    public Result deleteByType(Vehicle type) {
        return Result.success(vehicleMapper.deleteByType(type));
    }

    @Override
    public Result<List<Vehicle>> findTopRentByLimit(Integer number) {
        return Result.success(vehicleMapper.findTopRentByLimit(number));
    }

    @Override
    public Result<List<Vehicle>> findVehicleByType(Vehicle type) {
        return Result.success(vehicleMapper.findCarPageByType(0, 0, type));
    }
}
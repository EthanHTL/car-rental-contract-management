package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.sercive.VehicleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆信息表(Vehicle)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("/api/v1/car/vehicle")
public class VehicleController {
    /**
     * 服务对象
     */
    @Resource
    private VehicleService vehicleService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @PostMapping("/get")
    public Result<Vehicle> get(@RequestBody Vehicle vehicle) {
        return vehicleService.selectByPrimaryKey(vehicle.getId());
    }

    @PostMapping("/find/all")
    public Result<List<Vehicle>> findAll() {
        return vehicleService.findAll();
    }

    @PostMapping("/find/page")
    public Result<PageInfo<Vehicle>> findPage(@RequestBody Vehicle vehicle) {
        return vehicleService.findPage(vehicle);
    }

    @PostMapping("/create")
    public Result create(@RequestBody Vehicle vehicle) {
        return vehicleService.insert(vehicle);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }


}
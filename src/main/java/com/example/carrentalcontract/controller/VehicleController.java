package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.view.Vehicle;
import com.example.carrentalcontract.sercive.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 车辆信息表(Vehicle)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    /**
     * 服务对象
     */
    @Resource
    private VehicleService vehicleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Vehicle selectOne(Long id) {
        return this.vehicleService.queryById(id);
    }

}
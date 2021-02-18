package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.sercive.VehicleService;
import com.example.carrentalcontract.sercive.VehicleTypeService;
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
    @Resource
    private VehicleTypeService typeService;

    /**
     * 分页
     *
     * @return 单条数据
     */
    @PostMapping("/find/page")
    public Result<PageInfo<VehicleType>> findPage(@RequestBody VehicleType vehicle) {
        return typeService.findTypePage(vehicle);
    }




}
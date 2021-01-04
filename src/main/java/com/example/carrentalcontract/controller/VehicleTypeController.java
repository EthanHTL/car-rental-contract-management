package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 车辆类型表(VehicleType)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("vehicleType")
public class VehicleTypeController {
    /**
     * 服务对象
     */
    @Resource
    private VehicleTypeService vehicleTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public VehicleType selectOne(Long id) {
        return this.vehicleTypeService.queryById(id);
    }

}
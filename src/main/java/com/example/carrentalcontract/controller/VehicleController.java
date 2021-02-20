package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.sercive.VehicleService;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import com.example.carrentalcontract.util.FileHandler;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
     * 类别分页
     */
    @PostMapping("/type/find/page")
    public Result<PageInfo<VehicleType>> findTypePage(@RequestBody VehicleType type) {
        return typeService.findTypePage(type);
    }
    /**
     * 类别创建
     */
    @PostMapping("/type/create")
    public Result typeCreate(@RequestBody VehicleType type) {
        return typeService.create(type);
    }

    /**
     * 类别修改
     */
    @PostMapping("/type/update")
    public Result typeUpdate(@RequestBody VehicleType type) {
        return typeService.update(type);
    }

    /**
     * 类别删除
     */
    @PostMapping("/type/delete")
    public Result typeDelete(@RequestBody VehicleType type) {
        return typeService.delete(type);
    }



    /**
     * 分页
     */
    @PostMapping("/find/page")
    public Result<PageInfo<Vehicle>> findPage(@RequestBody Vehicle type) {

        return vehicleService.findCarPageByType(type);
    }
    /**
     * 创建
     */
    @PostMapping("/create")
    public Result create(@RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = "";
        try {
            url = FileHandler.singleFileUpload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(url);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody Vehicle vehicle) {
        return vehicleService.delete(vehicle);
    }



}
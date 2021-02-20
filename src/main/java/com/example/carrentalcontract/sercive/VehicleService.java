package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 车辆信息表(Vehicle)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleService extends DbService<Vehicle> {

    Result<PageInfo<Vehicle>> findCarPageByType(Vehicle vehicle);

    Result create(Vehicle type);

    Result update(Vehicle type);

    Result delete(Vehicle type);

    Result deleteByType(Vehicle type);


}
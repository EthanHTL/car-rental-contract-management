package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleTypeService extends DbService<VehicleType> {


    Result create(VehicleType type);

    Result delete(VehicleType type);

    Result update(VehicleType type);

    Result<PageInfo<VehicleType>> findTypePage(VehicleType type);


}
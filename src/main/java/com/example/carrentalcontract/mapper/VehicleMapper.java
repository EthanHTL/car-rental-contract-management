package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆信息表(Vehicle)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleMapper extends DbMapper<Vehicle> {

    List<Vehicle> findCarPageByType(Integer pageNum, Integer pageSize,@Param("car") VehicleType vehicle);

}
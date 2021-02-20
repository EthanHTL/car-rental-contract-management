package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.VehicleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆类型表(VehicleType)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleTypeMapper extends DbMapper<VehicleType> {
    List<VehicleType> findPage(Integer pageNum, Integer pageSize,@Param("type") VehicleType vehicle);
}
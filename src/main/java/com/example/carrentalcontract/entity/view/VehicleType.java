package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 车辆类型表(VehicleType)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
public class VehicleType implements Serializable {
    private static final long serialVersionUID = 337656210142209014L;
    /**
    * 编号
    */
    private Long id;
    /**
    * 车辆类型编号
    */
    private Long vehicleNo;
    /**
    * 类型名称
    */
    private String vehicleTypeName;
    /**
    * 库存
    */
    private Integer inventory;
    /**
    * 出租数量
    */
    private Integer rentOutNumber;
    /**
    * 备注
    */
    private String remark;



}
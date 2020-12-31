package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 车辆信息表(Vehicle)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 238482402972683356L;
    /**
    * 编号
    */
    private Long id;
    /**
    * 车辆编号
    */
    private String vehicleNumber;
    /**
    * 车辆名
    */
    private String vehicleName;
    /**
    * 车辆类型
    */
    private String vehicleType;
    /**
    * 车辆照片
    */
    private String pic;
    /**
    * 是否出过事故
    */
    private Object isAccident;
    /**
    * 是否出租
    */
    private Object isRentOut;


}
package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 车辆信息表(Vehicle)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
@Table(name = "vehicle")
public class Vehicle extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = 238482402972683356L;
    /**
     * 编号
     */
    private Long id;
    /**
     * 车辆编号
     */
    @Column(name = "vehicle_number")
    private String vehicleNumber;
    /**
     * 车辆名
     */
    @Column(name = "vehicle_name")
    private String vehicleName;
    /**
     * 车辆类型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;
    /**
     * 车辆照片
     */
    private String pic;
    /**
     * 是否出过事故
     */
    @Column(name = "is_accident")
    private Integer isAccident;
    /**
     * 是否出租
     */
    @Column(name = "is_rent_out")
    private Integer isRentOut;


}
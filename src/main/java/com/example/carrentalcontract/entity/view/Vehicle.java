package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 车辆信息表(Vehicle)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Object getIsAccident() {
        return isAccident;
    }

    public void setIsAccident(Object isAccident) {
        this.isAccident = isAccident;
    }

    public Object getIsRentOut() {
        return isRentOut;
    }

    public void setIsRentOut(Object isRentOut) {
        this.isRentOut = isRentOut;
    }

}
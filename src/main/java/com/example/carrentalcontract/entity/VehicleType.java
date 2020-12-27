package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 车辆类型表(VehicleType)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(Long vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getRentOutNumber() {
        return rentOutNumber;
    }

    public void setRentOutNumber(Integer rentOutNumber) {
        this.rentOutNumber = rentOutNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
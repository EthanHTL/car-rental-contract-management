package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 车辆类型表(VehicleType)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
public class VehicleType extends DbPageParameter implements Serializable {
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

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "updator_id")
    private String updatorId;

    @Column(name = "creator_id")
    private String creatorId;

}
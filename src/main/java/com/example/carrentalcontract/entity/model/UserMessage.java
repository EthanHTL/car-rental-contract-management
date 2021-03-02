package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user_message")
public class UserMessage extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -58234505158573253L;
    /**
    * 主键
    */
    @Id
    private Long id;
    /**
    * 用户编号
    */
    private Long userId;

    private String username;
    /**
    * 租车车辆编号
    */
    @Column(name = "vehicle_number")
    private Long vehicleNumber;
    /**
    * 消息
    */
    private String message;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 电话
    */
    private String phone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "appointment_time")
    private Date appointmentTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "updator_id")
    private String updatorId;

    @Column(name = "creator_id")
    private String creatorId;

    private Integer flag;



}
package com.example.carrentalcontract.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(Users)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 136163826059612169L;

    private String id;
    /**
    * 用户编号
    */
    private String account;
    /**
    * 性别
    */
    private Object sex;
    /**
    * 姓名
    */
    private String username;
    /**
     * 密码
     **/
    private String password;
    /**
    * 住址
    */
    private String addr;
    /**
    * 电话
    */
    private String telephone;
    /**
    * 身份证号
    */
    private String idCard;
    /**
    * 信誉分数
    */
    @Column(name = "reputation_score")
    private Integer reputationScore;
    /**
    * 状态
    */
    private Integer flag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;


}
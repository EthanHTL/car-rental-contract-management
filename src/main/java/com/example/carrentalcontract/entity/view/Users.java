package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import com.example.carrentalcontract.vdgroups.SaveGroup;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "用户验证不能为空!",groups = {SaveGroup.class})
    private String code;
    /**
    * 性别
    */
    private Object sex;
    /**
    * 姓名
    */
    @Column(name = "user_name")
    @NotBlank(message = "用户名不能为空!")
    private String userName;
    @NotBlank(message = "用户密码不能为空!")
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
    private Integer reputation;
    /**
    * 状态
    */
    private int status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "updator_id")
    private String updatorId;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "flag")
    private Integer flag;



}
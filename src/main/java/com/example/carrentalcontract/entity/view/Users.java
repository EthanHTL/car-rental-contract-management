package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(Users)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 136163826059612169L;
    /**
    * 用户编号
    */
    private Long code;
    /**
    * 性别
    */
    private Object sex;
    /**
    * 姓名
    */
    private String username;
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



}
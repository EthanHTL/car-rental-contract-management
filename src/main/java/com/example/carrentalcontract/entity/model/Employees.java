package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工信息表(Employees)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class Employees extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = 251589816155827495L;
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
    private String name;
    /**
    * 电话
    */
    private String telephone;
    /**
    * 身份证号
    */
    private String idCard;
    /**
    * 职称
    */
    private String title;



}
package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 用户表(Users)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
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
    private String 姓名;
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
    private Object 状态;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Object get状态() {
        return 状态;
    }

    public void set状态(Object 状态) {
        this.状态 = 状态;
    }

}
package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 员工信息表(Employees)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class Employees implements Serializable {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
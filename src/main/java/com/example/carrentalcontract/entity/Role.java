package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -78784730211500405L;
    /**
    * 角色id
    */
    private Long id;
    /**
    * 角色名
    */
    private String roleName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
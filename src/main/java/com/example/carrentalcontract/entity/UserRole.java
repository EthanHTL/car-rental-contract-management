package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 用户角色关联表(UserRole)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -58147659590939210L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 用户编号
    */
    private Long userId;
    /**
    * 角色id
    */
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
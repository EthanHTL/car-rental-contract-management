package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 角色权限关联表(RolePermission)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -49130009246332153L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 权限id
    */
    private Long permissionId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
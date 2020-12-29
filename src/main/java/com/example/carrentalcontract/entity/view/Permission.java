package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 权限表(Permission)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -35052749852424247L;
    /**
    * 权限id
    */
    private Long permission_id;
    /**
    * 权限名
    */
    private String permission;


    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
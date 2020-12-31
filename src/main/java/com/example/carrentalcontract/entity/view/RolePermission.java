package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联表(RolePermission)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class RolePermission extends DbPageParameter implements Serializable {
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


}
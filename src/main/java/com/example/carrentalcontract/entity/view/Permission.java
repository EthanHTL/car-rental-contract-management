package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表(Permission)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class Permission extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -35052749852424247L;
    /**
    * 权限id
    */
    private Long permission_id;
    /**
    * 权限名
    */
    private String permission;


}
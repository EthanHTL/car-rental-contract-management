package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class Role extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -78784730211500405L;
    /**
    * 角色id
    */
    private Long id;
    /**
    * 角色名
    */
    private String roleName;

}
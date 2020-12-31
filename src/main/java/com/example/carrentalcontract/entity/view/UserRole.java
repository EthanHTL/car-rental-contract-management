package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole extends DbPageParameter implements Serializable {
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



}
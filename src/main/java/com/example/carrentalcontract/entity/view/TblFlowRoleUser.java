package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class TblFlowRoleUser extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = 302526176059017614L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 流程角色名称
    */
    private String flowRoleName;
    /**
    * 员工编号
    */
    private Long userId;
    /**
    * 部门编号
    */
    private Long deptId;



}
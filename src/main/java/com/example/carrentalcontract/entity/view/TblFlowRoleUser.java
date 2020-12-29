package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 流程角色_员工表(TblFlowRoleUser)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class TblFlowRoleUser implements Serializable {
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowRoleName() {
        return flowRoleName;
    }

    public void setFlowRoleName(String flowRoleName) {
        this.flowRoleName = flowRoleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

}
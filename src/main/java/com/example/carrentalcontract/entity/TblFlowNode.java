package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 流程节点表(TblFlowNode)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class TblFlowNode implements Serializable {
    private static final long serialVersionUID = -95180659699240686L;
    /**
    * 流程节点编号，主键
    */
    private Long flowNodeId;
    /**
    * 流程号，与流程表对应
    */
    private Long flowNo;
    /**
    * 流程节点名称
    */
    private String flowNodeName;
    /**
    * 流程角色
    */
    private String flowNodeRole;
    /**
    * 备注
    */
    private String remark;


    public Long getFlowNodeId() {
        return flowNodeId;
    }

    public void setFlowNodeId(Long flowNodeId) {
        this.flowNodeId = flowNodeId;
    }

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public String getFlowNodeName() {
        return flowNodeName;
    }

    public void setFlowNodeName(String flowNodeName) {
        this.flowNodeName = flowNodeName;
    }

    public String getFlowNodeRole() {
        return flowNodeRole;
    }

    public void setFlowNodeRole(String flowNodeRole) {
        this.flowNodeRole = flowNodeRole;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 审批表(TblLeaveAudit)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class TblLeaveAudit implements Serializable {
    private static final long serialVersionUID = 226312535946754615L;
    /**
    * 审批编号，主键
    */
    private Long auditId;
    /**
    * 合同编号，与请假表对应
    */
    private Long leaveId;
    /**
    * 节点编号
    */
    private Long flowNodeId;
    /**
    * 审批人编号
    */
    private Long userId;
    /**
    * 审批人姓名
    */
    private String userName;
    /**
    * 审批意见
    */
    private String auditInfo;
    /**
    * 审批日期
    */
    private String auditDate;


    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Long getFlowNodeId() {
        return flowNodeId;
    }

    public void setFlowNodeId(Long flowNodeId) {
        this.flowNodeId = flowNodeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

}
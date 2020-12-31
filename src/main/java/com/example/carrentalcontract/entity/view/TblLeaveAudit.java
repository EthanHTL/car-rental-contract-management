package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class TblLeaveAudit extends DbPageParameter implements Serializable {
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



}
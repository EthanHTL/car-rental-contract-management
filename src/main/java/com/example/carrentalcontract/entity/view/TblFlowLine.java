package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 流程线表(TblFlowLine)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class TblFlowLine implements Serializable {
    private static final long serialVersionUID = 161130203487400867L;
    /**
    * 流程线编号，主键
    */
    private Long flowLineId;
    /**
    * 流程号，与流程表对应
    */
    private Long flowNo;
    /**
    * 前一节点编号
    */
    private Long prevNodeId;
    /**
    * 后一节点编号
    */
    private Long nextNodeId;
    /**
    * 备注
    */
    private String remark;


    public Long getFlowLineId() {
        return flowLineId;
    }

    public void setFlowLineId(Long flowLineId) {
        this.flowLineId = flowLineId;
    }

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public Long getPrevNodeId() {
        return prevNodeId;
    }

    public void setPrevNodeId(Long prevNodeId) {
        this.prevNodeId = prevNodeId;
    }

    public Long getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(Long nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
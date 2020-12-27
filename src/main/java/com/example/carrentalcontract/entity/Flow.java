package com.example.carrentalcontract.entity;

import java.io.Serializable;

/**
 * 流程表(Flow)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class Flow implements Serializable {
    private static final long serialVersionUID = -42676884829400034L;
    /**
    * 流程编号，主键
    */
    private Long flowId;
    /**
    * 流程号，惟一列
    */
    private Long flowNo;
    /**
    * 流程名称
    */
    private String flowName;
    /**
    * 备注
    */
    private String remark;


    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
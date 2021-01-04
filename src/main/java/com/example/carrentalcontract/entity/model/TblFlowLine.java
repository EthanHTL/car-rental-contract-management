package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程线表(TblFlowLine)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class TblFlowLine extends DbPageParameter implements Serializable {
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




}
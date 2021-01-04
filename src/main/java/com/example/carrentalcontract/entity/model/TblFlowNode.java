package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程节点表(TblFlowNode)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class TblFlowNode extends DbPageParameter implements Serializable {
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



}
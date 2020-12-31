package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserContract extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -58234505158573253L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 用户编号
    */
    private Long userId;
    /**
    * 租车车辆编号
    */
    private Long vehicleNumber;
    /**
    * 合同编号
    */
    private Long contractId;
    /**
    * 流程号
    */
    private Long flowNo;
    /**
    * 合同状态
    */
    private Integer status;
    /**
    * 当前节点编号
    */
    private Long currentNode;



}
package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 用户车辆合同管理表(UserContract)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public class UserContract implements Serializable {
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Long vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Long currentNode) {
        this.currentNode = currentNode;
    }

}
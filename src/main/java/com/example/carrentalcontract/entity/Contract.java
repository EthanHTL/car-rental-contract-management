package com.example.carrentalcontract.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 合同表(Contract)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:52
 */
public class Contract implements Serializable {
    private static final long serialVersionUID = -71828205063435859L;
    /**
    * 编号
    */
    private Long contractId;
    /**
    * 合同编号
    */
    private String contractNumbers;
    /**
    * 合同名称
    */
    private String contractName;
    /**
    * 签订单位
    */
    private String signUnit;
    /**
    * 支付方式
    */
    private String payment;
    /**
    * 合同负责人
    */
    private Long principal;
    /**
    * 客户联系人
    */
    private Long contactUserId;
    /**
    * 备注
    */
    private String remark;
    /**
    * 合同金额
    */
    private String contractAmount;
    /**
    * 已付金额
    */
    private String paidAmount;
    /**
    * 合同签订时间
    */
    private Date signTime;
    /**
    * 合同有效期
    */
    private Date endTime;
    /**
    * 合同到期时间
    */
    private Date contractLife;
    /**
    * 合同路径
    */
    private String contractUrl;
    /**
    * 合同类型
    */
    private Long contractType;


    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractNumbers() {
        return contractNumbers;
    }

    public void setContractNumbers(String contractNumbers) {
        this.contractNumbers = contractNumbers;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getSignUnit() {
        return signUnit;
    }

    public void setSignUnit(String signUnit) {
        this.signUnit = signUnit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Long getPrincipal() {
        return principal;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }

    public Long getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(Long contactUserId) {
        this.contactUserId = contactUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getContractLife() {
        return contractLife;
    }

    public void setContractLife(Date contractLife) {
        this.contractLife = contractLife;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Long getContractType() {
        return contractType;
    }

    public void setContractType(Long contractType) {
        this.contractType = contractType;
    }

}
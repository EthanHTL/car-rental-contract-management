package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.annotation.Dict;
import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.io.Serializable;

/**
 * 合同表(Contract)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:52
 */
@Data
@Table(name = "contract")
public class Contract extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -71828205063435859L;
    /**
    * 编号
    */
    @Id
    private Long contractId;
    /**
    * 合同编号
    */
    @Column(name = "contract_numbers")
    private String contractNumbers;
    /**
    * 合同名称
    */
    @Column(name = "contract_name")
    private String contractName;
    /**
    * 签订单位
    */
    @Column(name = "sign_unit")
    private String signUnit;
    /**
    * 支付方式
    */
    @Dict(dictCode = "payment")
    private String payment;
    /**
    * 合同负责人
    */
    private Long principal;
    /**
    * 客户联系人
    */
    @Column(name = "contact_user_id")
    private Long contactUserId;
    /**
    * 备注
    */
    private String remark;
    /**
    * 合同金额
    */
    @Column(name = "contract_amount")
    private String contractAmount;
    /**
    * 已付金额
    */
    @Column(name = "paid_amount")
    private String paidAmount;
    /**
    * 合同签订时间
    */
    @Column(name = "sign_time")
    private Date signTime;
    /**
    * 合同有效期
    */
    @Column(name = "end_time")
    private Date endTime;
    /**
    * 合同到期时间
    */
    @Column(name = "contract_life")
    private Date contractLife;
    /**
    * 合同路径
    */
    @Column(name = "contract_url")
    private String contractUrl;
    /**
    * 合同类型
    */
    @Column(name = "contract_type")
    private Long contractType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;


}
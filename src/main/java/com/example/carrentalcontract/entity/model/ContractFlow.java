package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 流程表(ContractFlow)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class ContractFlow extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -42676884829400034L;
    /**
    * 流程编号，主键
    */
    @Id
    private Long id;
    /**
     * 审批人id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 审批的合同id
     */
    @Column(name = "contract_id")
    private Long contractId;
    /**
     * 审批的流程id
     */
    @Column(name = "act_id")
    private String actId;
    /**
    * 审核状态 1：通过，0：不通过
    */
    private Integer state;
    /**
    * 备注
    */
    private String remark;

    private Integer flag;

    /**
     * 创建时间
     */
    @Transient
    private Date createTime;
    /**
     * 更新时间
     */
    @Transient
    private Date updateTime;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;

}
package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysFlow extends DbPageParameter implements Serializable {

    @Id
    private Long id;
    /**
     * 流程名
     */
    @Column(name = "flow_name")
    private String flowName;
    /**
     * 业务代码
     */
    @Column(name = "business_key")
    private String businessKey;
    /**
     * 状态，1：待审核，2：审核未通过，3：审核通过
     */
    @Column(name = "state")
    private Integer state;
    /**
     * 执行id
     */
    @Column(name = "execution_id")
    private String executionId;
    /**
     * 合同id
     */
    @Column(name = "contract_id")
    private Long contractId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     *
     */
    @Column(name = "process_definitionId")
    private String processDefinitionId;
    /**
     *
     */
    private String username;
    /**
     *
     */
    @Column(name = "process_instanceId")
    private String processInstanceId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 创建人
     */
    @Column(name = "creator_id")
    private String creatorId;
    /**
     * 修改人
     */
    @Column(name = "updator_id")
    private String updatorId;

    private Integer flag;



}

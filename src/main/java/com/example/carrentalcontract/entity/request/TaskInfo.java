package com.example.carrentalcontract.entity.request;

import lombok.Data;
import org.activiti.engine.task.DelegationState;

import java.util.Date;

@Data
public class TaskInfo {
    private String deploymentId;
    private String processInstanceId;
    private String processDefinitionId;
    private String processDefinitionName;
    private String assigneeUser;
    private Integer priority;
    private String owner;
    private String executionId;
    private Date createTime;
    private Date dueDate;
    private String taskName;
    private String assignee;
    private String assigneeUsername;
    private String category;
    private DelegationState delegationState;
    private String taskId;
    private Integer state;
    private String remark;
    private String description;
    private String businessKey;




}

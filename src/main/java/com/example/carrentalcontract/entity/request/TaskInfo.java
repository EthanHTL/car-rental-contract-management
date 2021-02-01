package com.example.carrentalcontract.entity.request;

import lombok.Data;

import java.util.Date;

@Data
public class TaskInfo {
    private String processInstanceId;
    private String processDefinitionId;
    private String assigneeUser;
    private String priority;
    private String executionId;
    private Date createTime;
    private String taskName;
    private String assignee;
    private String taskId;
    private Integer state;
    private String remark;



}

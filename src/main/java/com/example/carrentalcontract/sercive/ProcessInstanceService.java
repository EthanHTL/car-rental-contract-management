package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProcessInstanceService {
    Result getInstances();

    //启动
    Result startProcess(String processDefinitionKey,
                       String instanceName,
                       String instanceVariable,
                       String businessKey);

    //删除
    Result deleteInstance(String instanceID);

    //挂起冷冻
    Result suspendInstance(String instanceID);

    //激活
    Result resumeInstance(String instanceID);

    //获取参数
    Result variables(String instanceID);
}

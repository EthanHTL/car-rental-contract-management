package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.ContractFlow;

import java.util.Map;

public interface ContractFlowService extends DbService<ContractFlow> {

    /**
     * 设置流程变量
     */
    Map<String , Object> setVariables(Long id);

    /**
     * 整个流程开始时需要执行的任务
     */
    Result startRunTask(Long id, String formKey);

    /**
     * 整个流程结束需要执行的任务
     */
    void endRunTask(Long id);
}

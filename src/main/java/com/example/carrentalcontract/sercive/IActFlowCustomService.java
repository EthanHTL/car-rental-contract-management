package com.example.carrentalcontract.sercive;

import java.util.Map;

public interface IActFlowCustomService {

    /**
     * 设置流程变量
     */
    Map<String , Object> setVariables(Long id);

    /**
     * 整个流程开始时需要执行的任务
     */
    void startRunTask(Long id);

    /**
     * 整个流程结束需要执行的任务
     */
    void endRunTask(Long id);
}

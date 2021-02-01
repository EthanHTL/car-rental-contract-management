package com.example.carrentalcontract.listener.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-01 17:38
 **/
public class ContractErrorEndRunningTask  implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getParent().getProcessInstanceBusinessKey();
        // contract:1102020853784996
        System.out.println("合同审核不通过");
    }
}

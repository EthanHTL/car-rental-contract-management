package com.example.carrentalcontract.listener.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ContractEndRunningTask implements JavaDelegate {
    //重写委托的提交方法
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("合同审核结束");
    }
}

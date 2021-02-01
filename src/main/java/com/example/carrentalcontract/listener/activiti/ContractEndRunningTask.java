package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.sercive.ContractFlowService;
import com.example.carrentalcontract.sercive.ContractService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractEndRunningTask implements JavaDelegate {
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractFlowService contractFlowService;

    //重写委托的提交方法
    @Override
    public void execute(DelegateExecution delegateExecution) {
        // contractService
        System.out.println("合同审核通过");
        //    修改合同状态
    }
}

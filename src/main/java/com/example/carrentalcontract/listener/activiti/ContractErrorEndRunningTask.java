package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.sercive.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-01 17:38
 **/
@Slf4j
public class ContractErrorEndRunningTask implements JavaDelegate {
    @Autowired
    private ContractService contractService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String executionId = delegateExecution.getSuperExecutionId();
        String businessKey = delegateExecution.getParent().getProcessInstanceBusinessKey();
        // contract:1102020853784996
        String cid = businessKey.split(":")[1];
        Contract query = new Contract();
        query.setId(Long.getLong(cid));
        Contract flow = contractService.selectOne(query).getData();
        // 修改审核状态
        flow.setState(CheckEnum.NOT_PASS.getStatus());
        contractService.update(flow);
        log.info("businessKey：{},executionId:{},合同审核不通过。", businessKey, executionId);
    }
}

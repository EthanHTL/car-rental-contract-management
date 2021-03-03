package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.SysFlowService;
import com.example.carrentalcontract.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核通过
 */
@Slf4j
public class ContractEndRunningTask implements JavaDelegate {

    //重写委托的提交方法
    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) {
        // ContractService contractService = SpringContextUtil.getObject(ContractService.class);
        SysFlowService flowService = SpringContextUtil.getObject(SysFlowService.class);
        String exid = delegateExecution.getSuperExecutionId();
        String businessKey = delegateExecution.getParent().getProcessInstanceBusinessKey();
        String cid = businessKey.split(":")[1];
        // Contract query = new Contract();
        // query.setId(Long.parseLong(cid));
        // Contract contract = contractService.selectOne(query).getData();
        // 修改流程审核状态
        SysFlow query = new SysFlow();
        query.setBusinessKey(businessKey);
        SysFlow flow = flowService.selectOne(query).getData();
        flow.setState(3);
        flowService.update(flow);
        log.info("businessKey：{},executionId:{},合同审核通过。", businessKey, exid);
        //    修改合同状态
    }
}

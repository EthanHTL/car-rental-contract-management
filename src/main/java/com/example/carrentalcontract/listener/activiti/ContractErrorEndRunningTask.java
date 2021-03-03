package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.SysFlowService;
import com.example.carrentalcontract.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 审核不通过处理
 * @author: 黄天亮
 * @create: 2021-02-01 17:38
 **/
@Slf4j
public class ContractErrorEndRunningTask implements JavaDelegate {

    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) {
        ContractService contractService = SpringContextUtil.getObject(ContractService.class);
        SysFlowService flowService = SpringContextUtil.getObject(SysFlowService.class);
        String executionId = delegateExecution.getSuperExecutionId();
        String businessKey = delegateExecution.getParent().getProcessInstanceBusinessKey();
        // contract:1102020853784996
        String cid = businessKey.split(":")[1];
        Contract query = new Contract();
        query.setId(Long.parseLong(cid));
        Contract contract = contractService.selectOne(query).getData();
        contract.setState(2);
        contractService.update(contract);
        // 修改审核状态
        SysFlow flow = flowService.selectByContractId(Long.parseLong(cid)).getData();
        flow.setState(2);
        flowService.update(flow);
        log.info("businessKey：{},executionId:{},合同审核不通过。", businessKey, executionId);
    }
}

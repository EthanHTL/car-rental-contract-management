package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ContractEndRunningTask implements JavaDelegate {

    private ContractService contractService = SpringContextUtil.getObject(ContractService.class);

    //重写委托的提交方法
    @Override
    public void execute(DelegateExecution delegateExecution) {

        String exid = delegateExecution.getSuperExecutionId();
        String businessKey = delegateExecution.getParent().getProcessInstanceBusinessKey();
        String cid = businessKey.split(":")[1];
        Contract query = new Contract();
        query.setId(Long.parseLong(cid));
        Contract flow = contractService.selectOne(query).getData();
        // 修改审核状态
        flow.setState(CheckEnum.PASS.getStatus());
        contractService.update(flow);
        log.info("businessKey：{},executionId:{},合同审核通过。", businessKey, exid);
        System.out.println("合同审核通过");
        //    修改合同状态
    }
}

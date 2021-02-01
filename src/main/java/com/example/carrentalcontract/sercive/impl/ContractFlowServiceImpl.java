package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.ContractFlow;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.ContractFlowService;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-01 16:08
 **/
@Service
public class ContractFlowServiceImpl extends DbServiceImpl<ContractFlow> implements ContractFlowService {
    @Autowired
    private UsersService usersService;

    @Override
    public Map<String, Object> setVariables(Long id) {
        // 设置流程变量
        SysUser user = usersService.selectByPrimaryKey(id).getData();
        Map<String , Object> variables = new HashMap<>();
        variables.put("assignee0",1101121603322469L);// 用户1 张三
        variables.put("assignee1",1101110410767877L); // 业务1
        variables.put("assignee2",1101121505827021L); // 经理1
        variables.put("assignee3",1101021813661465L); // 总经理1

        return variables;
    }

    @Override
    public Result startRunTask(Long id, String formKey) {
        // 流程开启
        // contractService.
        ContractFlow flow =new ContractFlow();
        flow.setContractId(id);
        flow.setActId(formKey);
        // 合同审核状态
        flow.setState(CheckEnum.PENDING.getStatus());
        // contractFlowService
        return super.insert(flow);
    }

    @Override
    public void endRunTask(Long id) {

    }
}

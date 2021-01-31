package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.IActFlowCustomService;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IActFlowCustomServiceImpl implements IActFlowCustomService {

    @Autowired
    private UsersService usersService;
    /**
     * 设置流程变量
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> setVariables(Long id) {
        // 设置流程变量
        SysUser user = usersService.selectByPrimaryKey(id).getData();
        Map<String , Object> variables = new HashMap<>();
        variables.put("assignee0",1101110410767877L);// 业务员1
        variables.put("assignee1",1101121505827021L); // 经理1

        return variables;
    }

    @Override
    public void startRunTask(Long id) {
         // 流程开启

    }

    @Override
    public void endRunTask(Long id) {
        // 流程结束
    }
}

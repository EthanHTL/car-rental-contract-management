package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.sercive.SysFlowService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

@Service
public class SysFlowServiceImpl extends DbServiceImpl<SysFlow> implements SysFlowService {

    @Override
    public Result<List<SysFlow>> selectInPropertyIds(List<String> ids,String property) {
        Weekend<SysFlow> weekend = new Weekend<>(SysFlow.class);
        Example.Criteria criteria = weekend.createCriteria();
        if (ids.size() < 1) {
           return Result.success();
        }
        criteria.andIn(property,ids);
        return super.select(weekend);
    }

}


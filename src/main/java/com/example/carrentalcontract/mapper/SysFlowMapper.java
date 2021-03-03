package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;

public interface SysFlowMapper extends DbMapper<SysFlow> {
    SysFlow selectByContractId(Long id);
}

package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.view.FlowContractView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractMapper  extends DbMapper<Contract> {
    List<Contract> selectPassAll();

    List<FlowContractView> findPage(@Param("contract") FlowContractView contract, @Param("ids") List<Long> ids, Integer pageNum, Integer pageSize);
}

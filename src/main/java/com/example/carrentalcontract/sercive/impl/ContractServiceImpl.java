package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.mapper.ContractMapper;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.ProcessDefinitionService;
import com.example.carrentalcontract.sercive.ProcessInstanceService;
import com.example.carrentalcontract.sercive.UsersService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 合同表(Contract)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:55
 */
@Service("contractService")
public class ContractServiceImpl extends DbServiceImpl<Contract> implements ContractService {

    @Resource
    private ContractMapper contractMapper;
    @Autowired
    private ProcessDefinitionService definitionService;
    @Autowired
    private ProcessInstanceService instanceService;


    @Override
    public Result<List<Contract>> findAll() {
        return super.selectAll();
    }

    @PreAuthorize("hasAnyRole('common')")
    @Override
    public Result<PageInfo<Contract>> findPage(Contract contract) {
        PageInfo info = new PageInfo();
        Weekend<Contract> weekend = new Weekend<>(Contract.class);
        Example.Criteria criteria = weekend.createCriteria();
        if (StringUtils.isNotBlank(contract.getContractName())) {
            criteria.andLike("contractName", "%" + contract.getContractName() + "%");
        }

        return super.selectPage(weekend,contract.getPageNum(),contract.getPageSize());
    }

    @Override
    public Result<Contract> insert(@NonNull Contract contract) {
        return super.insert(contract);
    }

    @Transactional
    @Override
    public Result createContract(Contract contract) {
        // 创建合同
        Contract data = super.insert(contract).getData();
        // 启动合同审核流程
        instanceService.startProcess("contractActiviti",data.getContractName()
                ,"1"
                ,"Contract:"+data.getContractId());
        return null;
    }


}
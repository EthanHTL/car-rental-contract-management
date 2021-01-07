package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.mapper.ContractMapper;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.UsersService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
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

    // @Override
    // public Result<PageInfo<List<Contract>>> findPage(Contract contract) {
    //
    //     PageHelper.startPage(contract.getPageNum(), contract.getPageSize());
    //     Page<Contract> contracts = contractMapper.all();
    //     PageInfo pageInfo = new PageInfo<>(contracts);
    //     return new Result(pageInfo);
    // }




}
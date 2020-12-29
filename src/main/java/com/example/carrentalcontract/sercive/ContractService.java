package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Contract;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 合同表(Contract)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:54
 */
public interface ContractService {

    Result<List<Contract>> selectAll();

    Result<PageInfo<List<Contract>>> findPage(Contract contract);

    Result<Contract> get(Contract contract);
}
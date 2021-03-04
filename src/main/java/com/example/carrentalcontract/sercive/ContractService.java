package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.entity.view.FlowContractView;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 合同表(Contract)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:54
 */
public interface ContractService extends DbService<Contract> {

    Result<PageInfo<FlowContractView>> findPage(Contract contract);

    Result<List<Contract>> findAll();

    Result insert(Contract contract);

    Result createContract(Contract contract , SysUser user);

    Map<String, Object> setVariables(Long id);

    Result<List<FlowContractView>> findMyTask(List<TaskInfo> infos);

    Result<PageInfo<FlowContractView>> selectInIds(FlowContractView contract, List<Long> ids);

    Result renewContract(Contract contract, SysUser currentUser);

    Result<List<Contract>> selectPassAll();
}
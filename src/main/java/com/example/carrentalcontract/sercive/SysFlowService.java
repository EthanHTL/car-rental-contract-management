package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysFlow;

import java.util.List;

public interface SysFlowService extends DbService<SysFlow> {

    Result<List<SysFlow>> selectInPropertyIds(List<String> ids, String property);
}

package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;

import java.util.List;

public interface SysDictDetailService extends DbService<SysDictDetail> {

    Result<SysDictDetail> getDictDataByTypeAndValue(String dictType, String key);

    Result<List<SysDictDetail>> findDetailByCode(SysDict dict);
}

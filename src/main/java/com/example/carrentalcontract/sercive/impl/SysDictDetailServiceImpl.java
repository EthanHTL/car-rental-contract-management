package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.example.carrentalcontract.mapper.SysDictDetailMapper;
import com.example.carrentalcontract.sercive.SysDictDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends DbServiceImpl<SysDictDetail>  implements SysDictDetailService {
    @Resource
    private SysDictDetailMapper sysDictDetailMapper;

    @Override
    public Result<SysDictDetail> getDictDataByTypeAndValue(String dictType, String key) {
        SysDictDetail detail = sysDictDetailMapper.getDictDataByTypeAndValue(dictType, key);
        return Result.success(detail);
    }

    @Override
    public Result<List<SysDictDetail>> findDetailByCode(SysDict dict) {
        return Result.success(sysDictDetailMapper.findDetailByCode(dict.getCode()));
    }
}

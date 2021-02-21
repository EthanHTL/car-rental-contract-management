package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.example.carrentalcontract.mapper.SysDictDetailMapper;
import com.example.carrentalcontract.sercive.SysDictDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Result<SysDictDetail> getDictDataByTypeAndId(String dictType, String key) {
        SysDictDetail detail = sysDictDetailMapper.getDictDataByTypeAndId(dictType, key);
        return Result.success(detail);
    }

    @Override
    public Result<List<SysDictDetail>> findDetailByCode(SysDict dict) {
        List<SysDictDetail> detailByCode = sysDictDetailMapper.findDetailByCode(dict.getCode());
        return Result.success(detailByCode);
    }
}

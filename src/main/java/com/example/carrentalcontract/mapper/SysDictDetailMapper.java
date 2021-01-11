package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictDetailMapper extends DbMapper<SysDictDetail> {

    SysDictDetail getDictDataByTypeAndValue(@Param("dicType") String dictType,@Param("key") String key);

    List<SysDictDetail> findDetailByCode(@Param("dicType") String dicType);
}

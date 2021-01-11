package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 数据字典(DataDictionary)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface SysDictService {


    Result<SysDict> selectByPrimaryKey(Long typeCode);

    Result<List<SysDict>> findAll();

    Result<PageInfo<SysDict>> findPage(SysDict dictionary);

    Result insert(SysDict sysDict);

    Result update(SysDict sysDict);

    Result delete(SysDict dictionary);

    Result destory(SysDict dictionary);



}
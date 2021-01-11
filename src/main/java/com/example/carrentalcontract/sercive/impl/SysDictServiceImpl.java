package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.example.carrentalcontract.mapper.SysDictDetailMapper;
import com.example.carrentalcontract.mapper.SysDictMapper;
import com.example.carrentalcontract.sercive.SysDictService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典(DataDictionary)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("sysDictService")
public class SysDictServiceImpl extends DbServiceImpl<SysDict> implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;

    @Resource
    SysDictDetailMapper sysDictDetailMapper;


    @Override
    public Result destory(SysDict dict) {
        return super.destroy(dict);
    }

    @Override
    public Result<List<SysDict>> findAll() {
        return super.selectAll();
    }

    @Override
    public Result<SysDict> selectByPrimaryKey(Long typeCode) {
        return super.selectByPrimaryKey(typeCode);
    }

    @Override
    public Result<PageInfo<SysDict>> findPage(SysDict dict) {
        PageInfo info = new PageInfo();
        Weekend<SysDict> weekend = new Weekend<>(SysDict.class);
        Example.Criteria criteria = weekend.createCriteria();
        if (StringUtils.isNotBlank(dict.getName())) {
            criteria.andLike("typeName", "%" + dict.getName() + "%");
        }

        return super.selectPage(weekend,dict.getPageNum(),dict.getPageSize());

    }

    @Override
    public Result update(SysDict dict) {
        return super.update(dict);
    }

    @Override
    public Result<SysDict> insert(SysDict dict) {
        return super.insert(dict);
    }

    @Override
    public Result delete(SysDict dict) {
        return null;
    }
}
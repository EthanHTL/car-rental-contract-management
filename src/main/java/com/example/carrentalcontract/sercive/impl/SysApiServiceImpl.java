package com.example.carrentalcontract.sercive.impl;


import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.mapper.SysApiMapper;
import com.example.carrentalcontract.sercive.SysApiService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;

/**
 * 权限表(SysApi)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 17:24:13
 */
@Service("sysApiService")
public class SysApiServiceImpl extends DbServiceImpl<SysApi> implements SysApiService {

    @Resource
    private SysApiMapper apiMapper;

    @Override
    public Result<PageInfo<SysApi>> findPage(SysApi sysApi) {

        Weekend<SysApi> weekend = new Weekend<>(SysApi.class);
        Example.Criteria criteria = weekend.createCriteria();

        if (StringUtils.isNotBlank(sysApi.getApiName())) {
            criteria.andLike("apiName", "%" + sysApi.getApiName() + "%");
        }
        return super.selectPage(weekend,sysApi.getPageNum(),sysApi.getPageSize());
    }

    @Override
    public Result<SysApi> insert(SysApi sysApi) {
        return super.insert(sysApi);
    }

    @Override
    public Result update(SysApi sysApi) {
        return super.update(sysApi);
    }

    @Override
    public Result delete(SysApi sysApi) {
        return super.destroy(sysApi);
    }
}
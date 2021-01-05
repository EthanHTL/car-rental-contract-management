package com.example.carrentalcontract.sercive.impl;


import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.sercive.SysApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysApi)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 17:24:13
 */
@Service("sysApiService")
public class SysApiMapperImpl extends DbServiceImpl<SysApi> implements SysApiService {

    @Resource
    private SysApiService sysApiService;

}
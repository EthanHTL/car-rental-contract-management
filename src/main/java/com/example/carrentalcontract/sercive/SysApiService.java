package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysApi;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;

import java.util.List;

/**
 * 权限表(SysApi)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 17:24:13
 */
public interface SysApiService extends DbService<SysApi> {


    Result<PageInfo<SysApi>> findPage(SysApi sysApi);

    Result<SysApi> insert(@NonNull SysApi sysApi);

    Result delete(SysApi sysApi);

    Result update(SysApi sysApi);
}
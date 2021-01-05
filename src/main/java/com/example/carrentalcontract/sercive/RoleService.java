package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysRole;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface RoleService extends DbService<SysRole> {


    Result<List<SysRole>> findAll();
}
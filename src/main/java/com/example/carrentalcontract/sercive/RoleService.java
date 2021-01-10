package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.request.SysRoleRequest;
import com.example.carrentalcontract.entity.request.SysUserRequest;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface RoleService extends DbService<SysRole> {


    /**
     * 查询所有角色
     */
    Result<List<SysRole>> findAll();

    /**
     * 单个查询
     */
    Result<SysRole> selectByPrimaryKey(Long id);


    /**
     * 创建角色
     */
    Result insert(SysRole role);


    Result insertUserRole(SysUserRequest user);

    /**
     * 修改角色
     */
    Result update(SysRole role);


    Result<SysRoleRequest> findSecurity(SysRole role);
}
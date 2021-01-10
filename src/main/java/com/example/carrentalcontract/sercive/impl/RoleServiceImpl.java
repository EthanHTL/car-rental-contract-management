package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.request.SysUserRequest;
import com.example.carrentalcontract.mapper.RoleMapper;
import com.example.carrentalcontract.sercive.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("roleService")
public class RoleServiceImpl extends DbServiceImpl<SysRole> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result<List<SysRole>> findAll() {

        return Result.success(roleMapper.findRoleByUserName("111"));
        // return super.selectAll();
    }

    @Override
    public Result insert(SysRole role) {
        return super.insertOne(role);
    }

    @Override
    public Result insertUserRole(SysUserRequest user) {
        return null;
    }

    @Override
    public Result<SysRole> selectByPrimaryKey(Long id) {
        return super.selectByPrimaryKey(id);
    }


}
package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.SysUserRequest;
import com.example.carrentalcontract.entity.response.SysRoleResponseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface RoleMapper extends DbMapper<SysRole> {

    List<SysRole> findRoleByUserName(@Param("username") String username);


    int insertUserAndRole(List<SysRole> list, @Param("id") Long id);

    List<SysUser> findUsersByRole(@Param("role") SysRole role);

    List<SysRole> findRolesByUser(@Param("user") SysUser user);

    void insertBatchMenuPermission(SysRoleResponseInfo info);

    void insertBatchApiPermission(SysRoleResponseInfo info);

    void deleteApiIds(SysRoleResponseInfo info);

    void deleteMenuIds(SysRoleResponseInfo info);

    void deleteRoleByUser(SysUserRequest user);

    void deleteApiByRole(SysRole role);

    void deleteMenuByRole(SysRole role);

    void deleteRoleUser(SysRole role);
}
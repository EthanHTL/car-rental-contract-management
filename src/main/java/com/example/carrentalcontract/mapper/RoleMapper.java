package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface RoleMapper extends DbMapper<SysRole> {

    List<SysRole> findRoleByUserName(@Param("username") String username);

}
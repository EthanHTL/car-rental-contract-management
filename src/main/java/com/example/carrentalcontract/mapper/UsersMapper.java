package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UsersMapper extends DbMapper<SysUser> {


    List<SysUser> findEmployeeAll(SysUser employee);

    List<SysUser> findCustomerAll(SysUser user);
}
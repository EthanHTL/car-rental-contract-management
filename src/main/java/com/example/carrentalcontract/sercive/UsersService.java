package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UsersService extends DbService<SysUser> {


    Result insert(SysUser user);

    Result update(SysUser user);
}
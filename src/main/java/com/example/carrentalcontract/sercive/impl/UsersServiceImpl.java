package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.UserEnum;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("usersService")
public class UsersServiceImpl extends DbServiceImpl<SysUser> implements UsersService {

    @Resource
    UsersMapper userMapper;
    @Autowired
    RoleService roleService;



    @Override
    public Result insert(SysUser user) {
        // 账号查重
        if (checkUserCode(user)) {
            return new Result(UserEnum.USER_READY.getStatusCode(), UserEnum.USER_READY.getMessage());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bsPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bsPassword);
        return super.insert(user);
    }

    @Override
    public Result update(SysUser sysUser) {
        return super.update(sysUser);
    }





    private boolean checkUserCode(SysUser user) {
        Weekend<SysUser> weekend = new Weekend<>(SysUser.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        List<SysUser> data = select(weekend).getData();
        return data.size() > 0;
    }
}
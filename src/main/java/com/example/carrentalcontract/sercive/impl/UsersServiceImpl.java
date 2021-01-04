package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.UserEnum;
import com.example.carrentalcontract.entity.model.Users;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * 用户表(Users)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("usersService")
public class UsersServiceImpl extends DbServiceImpl<Users> implements UsersService {


    @Override
    public Result insert(Users user) {
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
    public Result update(Users users) {
        return super.update(users);
    }

    private boolean checkUserCode(Users user) {
        Weekend<Users> weekend = new Weekend<>(Users.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("account", user.getAccount());
        List<Users> data = select(weekend).getData();
        return data.size() > 0;
    }
}
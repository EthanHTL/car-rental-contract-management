package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Contract;
import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.example.carrentalcontract.sercive.UsersService;
import lombok.NonNull;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
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
            return new Result(901, "账号已存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bsPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bsPassword);
        System.out.println(user);
        return super.insert(user);
    }

    @Override
    public Result update(Users users) {
        return super.update(users);
    }

    private boolean checkUserCode(Users user) {
        Weekend<Contract> weekend = new Weekend<>(Contract.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("code", user.getCode());
        List<Users> data = select(weekend).getData();
        return data.size() > 0;
    }
}
package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.example.carrentalcontract.sercive.UsersService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Result<Users> insert( Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bsPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bsPassword);
        System.out.println(user);

        return super.insert(user);
    }
}
package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Users;

import java.util.List;

/**
 * 用户表(Users)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UsersService {

    Result insert(Users user);

}
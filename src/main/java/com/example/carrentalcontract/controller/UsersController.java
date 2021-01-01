package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.sercive.UsersService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(Users)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("/api/v1/car/users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    @PostMapping("/register")
    public Result register(@RequestBody Users user){
        return usersService.insert(user);
    }

}
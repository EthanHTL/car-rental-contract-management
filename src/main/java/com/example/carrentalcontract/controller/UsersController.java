package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
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


    @NotNull(field = "username", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/register")
    public Result register(@RequestBody SysUser user) {
        return usersService.insert(user);
    }

    @NotNull(field = "id", name = "主键", statusCode = 701)
    @PostMapping("/update/password")
    public Result updatePassword(@RequestBody SysUser user) {
        return usersService.update(user);
    }


}
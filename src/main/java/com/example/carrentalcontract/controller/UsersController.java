package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


    /**
     * 注册
     *
     * @param user user
     */
    @NotNull(field = "username", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/register")
    public Result register(@RequestBody SysUser user) {
        return usersService.insert(user);
    }

    /**
     * 查询所有用户
     *
     * @param user 用户
     */
    @NotNull(field = "id", name = "主键", statusCode = 701)
    @NotNull(field = "username", name = "账户", statusCode = 702)
    @PostMapping("/update/password")
    public Result updatePassword(@RequestBody SysUser user) {
        return usersService.updatePassword(user);
    }


    /**
     * 跟新操作
     *
     * @param user 用户
     */
    @NotNull(field = "id", name = "主键", statusCode = 701)
    @PostMapping("/update")
    public Result update(@RequestBody SysUser user) {
        return usersService.update(user);
    }

    @PostMapping("/find/password")
    public Result<SysUser> getOldPassword() {
        return usersService.selectByUsername(getUserInfo().getUsername());
    }

    public UserDetails getUserInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
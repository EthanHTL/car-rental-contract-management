package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.sercive.UsersService;
import com.example.carrentalcontract.vdgroups.SaveGroup;
import org.apache.catalina.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;
import javax.validation.Valid;
import java.util.List;

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


    @NotNull(field = "code", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/register")
    public Result register(@RequestBody Users user) {
        return usersService.insert(user);
    }

    @NotNull(field = "id", name = "主键", statusCode = 701)
    @PostMapping("/update/password")
    public Result updatePassword(@RequestBody Users user) {
        return usersService.update(user);
    }


}
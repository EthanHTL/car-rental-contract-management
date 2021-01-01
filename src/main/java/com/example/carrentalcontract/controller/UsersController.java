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


    @PostMapping("/register")
    public Result register(@Validated({SaveGroup.class}) @RequestBody Users user, BindingResult result){
        if(result.hasErrors()){
            Result<List<ObjectError>> listResult = new Result<>(result.getAllErrors());
            listResult.setStatusCode(400);
            return listResult;
        }
        return usersService.insert(user);
    }

    @NotNull(status = "400",message = "用户验证码不能为空!")
    @PostMapping("/register2")
    public Result register2(@RequestBody Users user){
        return usersService.insert(user);
    }

}
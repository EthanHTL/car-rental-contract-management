package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.model.UserRole;
import com.example.carrentalcontract.sercive.UserRoleService;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联表(UserRole)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("/api/v1/car/users/manager")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UsersService usersService;


    @PostMapping("/find/employee/all")
    public Result<List<SysUser>> findAll(){
        return usersService.findEmployeeAll();
    }


}
package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Employees;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.EmployeesService;
import com.example.carrentalcontract.sercive.UsersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工信息表(Employees)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/users/manager")
public class EmployeesController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeesService employeesService;

    @Resource
    private UsersService usersService;


    @PostMapping("/find/employee/page")
    public Result<PageInfo<SysUser>> findPage(@RequestBody SysUser employee){
        return usersService.findEmployeePage(employee);
    }

}
package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.Employees;
import com.example.carrentalcontract.sercive.EmployeesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 员工信息表(Employees)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("employees")
public class EmployeesController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeesService employeesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Employees selectOne(Long id) {
        return this.employeesService.queryById(id);
    }

}
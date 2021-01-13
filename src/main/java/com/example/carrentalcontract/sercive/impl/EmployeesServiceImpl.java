package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.model.Employees;
import com.example.carrentalcontract.mapper.EmployeesMapper;
import com.example.carrentalcontract.sercive.EmployeesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工信息表(Employees)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService {
    @Resource
    private EmployeesMapper employeesDao;


}
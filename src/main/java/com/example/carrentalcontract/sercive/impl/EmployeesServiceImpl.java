package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.Employees;
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

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    @Override
    public Employees queryById(Long code) {
        return this.employeesDao.queryById(code);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Employees> queryAllByLimit(int offset, int limit) {
        return this.employeesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    @Override
    public Employees insert(Employees employees) {
        this.employeesDao.insert(employees);
        return employees;
    }

    /**
     * 修改数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    @Override
    public Employees update(Employees employees) {
        this.employeesDao.update(employees);
        return this.queryById(employees.getCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long code) {
        return this.employeesDao.deleteById(code) > 0;
    }
}
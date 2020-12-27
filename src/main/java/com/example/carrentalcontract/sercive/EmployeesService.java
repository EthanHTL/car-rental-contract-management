package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.Employees;

import java.util.List;

/**
 * 员工信息表(Employees)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface EmployeesService {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Employees queryById(Long code);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Employees> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    Employees insert(Employees employees);

    /**
     * 修改数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    Employees update(Employees employees);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    boolean deleteById(Long code);

}
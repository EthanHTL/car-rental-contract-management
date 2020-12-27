package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.Employees;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 员工信息表(Employees)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface EmployeesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Employees queryById(Long code);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Employees> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param employees 实例对象
     * @return 对象列表
     */
    List<Employees> queryAll(Employees employees);

    /**
     * 新增数据
     *
     * @param employees 实例对象
     * @return 影响行数
     */
    int insert(Employees employees);

    /**
     * 修改数据
     *
     * @param employees 实例对象
     * @return 影响行数
     */
    int update(Employees employees);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 影响行数
     */
    int deleteById(Long code);

}
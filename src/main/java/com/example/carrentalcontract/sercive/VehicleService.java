package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 车辆信息表(Vehicle)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleService {

    /**
     * 通过ID查询单条数据
     */
    Result<Vehicle> selectByPrimaryKey(Long id);

    /**
     * 查询多条数据
     *
     * @param vehicle 车辆信息
     * @return 对象列表
     */
    Result<PageInfo<Vehicle>> findPage(Vehicle vehicle);
    
    /**
     * 插入
     *
     * @param vehicle 车辆
     */
    Result insert(Vehicle vehicle);

    /**
     * 批量插入
     *
     * @param vehicles 车辆
     */
    Result insertBatch(List<Vehicle> vehicles);

    /**
     * 修改数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    Result update(Vehicle vehicle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    Result<List<Vehicle>> findAll();
}
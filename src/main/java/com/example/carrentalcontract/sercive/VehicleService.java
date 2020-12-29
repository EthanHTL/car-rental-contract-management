package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.view.Vehicle;

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
     *
     * @param id 主键
     * @return 实例对象
     */
    Vehicle queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vehicle> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    Vehicle insert(Vehicle vehicle);

    /**
     * 修改数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    Vehicle update(Vehicle vehicle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
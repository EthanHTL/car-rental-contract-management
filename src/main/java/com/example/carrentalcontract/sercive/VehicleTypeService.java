package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.VehicleType;

import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VehicleType queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VehicleType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vehicleType 实例对象
     * @return 实例对象
     */
    VehicleType insert(VehicleType vehicleType);

    /**
     * 修改数据
     *
     * @param vehicleType 实例对象
     * @return 实例对象
     */
    VehicleType update(VehicleType vehicleType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
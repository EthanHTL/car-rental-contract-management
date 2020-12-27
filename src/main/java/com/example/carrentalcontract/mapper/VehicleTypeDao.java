package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.VehicleType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 车辆类型表(VehicleType)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface VehicleTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VehicleType queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VehicleType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vehicleType 实例对象
     * @return 对象列表
     */
    List<VehicleType> queryAll(VehicleType vehicleType);

    /**
     * 新增数据
     *
     * @param vehicleType 实例对象
     * @return 影响行数
     */
    int insert(VehicleType vehicleType);

    /**
     * 修改数据
     *
     * @param vehicleType 实例对象
     * @return 影响行数
     */
    int update(VehicleType vehicleType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.VehicleType;
import com.example.carrentalcontract.mapper.VehicleTypeMapper;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleTypeService")
public class VehicleTypeServiceImpl implements VehicleTypeService {
    @Resource
    private VehicleTypeMapper vehicleTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VehicleType queryById(Long id) {
        return this.vehicleTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<VehicleType> queryAllByLimit(int offset, int limit) {
        return this.vehicleTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vehicleType 实例对象
     * @return 实例对象
     */
    @Override
    public VehicleType insert(VehicleType vehicleType) {
        this.vehicleTypeDao.insert(vehicleType);
        return vehicleType;
    }

    /**
     * 修改数据
     *
     * @param vehicleType 实例对象
     * @return 实例对象
     */
    @Override
    public VehicleType update(VehicleType vehicleType) {
        this.vehicleTypeDao.update(vehicleType);
        return this.queryById(vehicleType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.vehicleTypeDao.deleteById(id) > 0;
    }
}
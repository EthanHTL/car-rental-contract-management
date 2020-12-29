package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.view.Vehicle;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.sercive.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆信息表(Vehicle)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Vehicle queryById(Long id) {
        return this.vehicleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Vehicle> queryAllByLimit(int offset, int limit) {
        return this.vehicleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    @Override
    public Vehicle insert(Vehicle vehicle) {
        this.vehicleDao.insert(vehicle);
        return vehicle;
    }

    /**
     * 修改数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    @Override
    public Vehicle update(Vehicle vehicle) {
        this.vehicleDao.update(vehicle);
        return this.queryById(vehicle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.vehicleDao.deleteById(id) > 0;
    }
}
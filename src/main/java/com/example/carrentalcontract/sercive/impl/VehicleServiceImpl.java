package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.sercive.VehicleService;
import com.github.pagehelper.PageInfo;
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
public class VehicleServiceImpl extends DbServiceImpl<Vehicle> implements VehicleService {
    @Resource
    private VehicleMapper vehicleDao;

    /**
     * 主键查询
     */
    @Override
    public Result<Vehicle> selectByPrimaryKey(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Result<PageInfo<Vehicle>> findPage(Vehicle vehicle) {
        return super.selectPage(vehicle,vehicle.getPageNum(),vehicle.getPageSize());
    }


    /**
     * 新增数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(Vehicle vehicle) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param vehicle 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Vehicle vehicle) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Result<List<Vehicle>> findAll() {
        return null;
    }
}
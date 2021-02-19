package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.mapper.VehicleMapper;
import com.example.carrentalcontract.mapper.VehicleTypeMapper;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆类型表(VehicleType)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("vehicleTypeService")
public class VehicleTypeServiceImpl extends DbServiceImpl<VehicleType> implements VehicleTypeService {

    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private VehicleTypeMapper vehicleTypeMapper;

    @Override
    public Result create(VehicleType type) {
        return super.insert(type);
    }

    @Override
    public Result update(@NonNull VehicleType type) {
        return super.update(type);
    }

    @Override
    public Result delete(@NonNull VehicleType type) {
        return super.delete(type);
    }

    @Override
    public Result<PageInfo<VehicleType>> findTypePage(VehicleType type) {
        PageInfo<VehicleType> pageInfo = new PageInfo<>();

        Weekend<VehicleType> weekend = new Weekend<>(VehicleType.class);
        Example.Criteria criteria = weekend.createCriteria();

        pageInfo = super.selectPage(weekend, type.getPageNum(), type.getPageSize()).getData();
        return Result.success(pageInfo);
    }


}
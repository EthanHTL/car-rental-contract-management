package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.model.UserContract;
import com.example.carrentalcontract.mapper.UserContractMapper;
import com.example.carrentalcontract.sercive.UserContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户车辆合同管理表(UserContract)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("userContractService")
public class UserContractServiceImpl implements UserContractService {
    @Resource
    private UserContractMapper userContractDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserContract queryById(Long id) {
        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserContract> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param userContract 实例对象
     * @return 实例对象
     */
    @Override
    public UserContract insert(UserContract userContract) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param userContract 实例对象
     * @return 实例对象
     */
    @Override
    public UserContract update(UserContract userContract) {
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
}
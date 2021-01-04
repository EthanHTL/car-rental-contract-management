package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.model.TblFlowRoleUser;
import com.example.carrentalcontract.mapper.TblFlowRoleUserMapper;
import com.example.carrentalcontract.sercive.TblFlowRoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程角色_员工表(TblFlowRoleUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("tblFlowRoleUserService")
public class TblFlowRoleUserServiceImpl implements TblFlowRoleUserService {
    @Resource
    private TblFlowRoleUserMapper tblFlowRoleUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TblFlowRoleUser queryById(Long id) {
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
    public List<TblFlowRoleUser> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param tblFlowRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowRoleUser insert(TblFlowRoleUser tblFlowRoleUser) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param tblFlowRoleUser 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowRoleUser update(TblFlowRoleUser tblFlowRoleUser) {
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
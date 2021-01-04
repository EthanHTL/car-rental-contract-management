package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.model.Permission;
import com.example.carrentalcontract.mapper.PermissionMapper;
import com.example.carrentalcontract.sercive.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(Permission)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param 权限id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Long 权限id) {
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
    public List<Permission> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param 权限id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long 权限id) {
        return false;
    }
}
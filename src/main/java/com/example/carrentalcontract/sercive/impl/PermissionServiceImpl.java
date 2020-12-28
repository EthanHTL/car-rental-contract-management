package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.Permission;
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
        return this.permissionDao.queryById(权限id);
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
        return this.permissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getPermission_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param 权限id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long 权限id) {
        return this.permissionDao.deleteById(权限id) > 0;
    }
}
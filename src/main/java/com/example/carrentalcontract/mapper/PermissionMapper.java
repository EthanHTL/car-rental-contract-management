package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.view.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface PermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param 权限id 主键
     * @return 实例对象
     */
    Permission queryById(Long 权限id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryAll(Permission permission);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param 权限id 主键
     * @return 影响行数
     */
    int deleteById(Long 权限id);

}
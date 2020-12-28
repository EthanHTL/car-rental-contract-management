package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.Flow;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 流程表(Flow)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface FlowMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param flowId 主键
     * @return 实例对象
     */
    Flow queryById(Long flowId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Flow> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param flow 实例对象
     * @return 对象列表
     */
    List<Flow> queryAll(Flow flow);

    /**
     * 新增数据
     *
     * @param flow 实例对象
     * @return 影响行数
     */
    int insert(Flow flow);

    /**
     * 修改数据
     *
     * @param flow 实例对象
     * @return 影响行数
     */
    int update(Flow flow);

    /**
     * 通过主键删除数据
     *
     * @param flowId 主键
     * @return 影响行数
     */
    int deleteById(Long flowId);

}
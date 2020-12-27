package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.TblFlowNode;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 流程节点表(TblFlowNode)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface TblFlowNodeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param flowNodeId 主键
     * @return 实例对象
     */
    TblFlowNode queryById(Long flowNodeId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblFlowNode> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tblFlowNode 实例对象
     * @return 对象列表
     */
    List<TblFlowNode> queryAll(TblFlowNode tblFlowNode);

    /**
     * 新增数据
     *
     * @param tblFlowNode 实例对象
     * @return 影响行数
     */
    int insert(TblFlowNode tblFlowNode);

    /**
     * 修改数据
     *
     * @param tblFlowNode 实例对象
     * @return 影响行数
     */
    int update(TblFlowNode tblFlowNode);

    /**
     * 通过主键删除数据
     *
     * @param flowNodeId 主键
     * @return 影响行数
     */
    int deleteById(Long flowNodeId);

}
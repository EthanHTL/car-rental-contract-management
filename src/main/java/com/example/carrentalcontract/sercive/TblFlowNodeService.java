package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.view.TblFlowNode;

import java.util.List;

/**
 * 流程节点表(TblFlowNode)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface TblFlowNodeService {

    /**
     * 通过ID查询单条数据
     *
     * @param flowNodeId 主键
     * @return 实例对象
     */
    TblFlowNode queryById(Long flowNodeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblFlowNode> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tblFlowNode 实例对象
     * @return 实例对象
     */
    TblFlowNode insert(TblFlowNode tblFlowNode);

    /**
     * 修改数据
     *
     * @param tblFlowNode 实例对象
     * @return 实例对象
     */
    TblFlowNode update(TblFlowNode tblFlowNode);

    /**
     * 通过主键删除数据
     *
     * @param flowNodeId 主键
     * @return 是否成功
     */
    boolean deleteById(Long flowNodeId);

}
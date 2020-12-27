package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.Flow;

import java.util.List;

/**
 * 流程表(Flow)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface FlowService {

    /**
     * 通过ID查询单条数据
     *
     * @param flowId 主键
     * @return 实例对象
     */
    Flow queryById(Long flowId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Flow> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param flow 实例对象
     * @return 实例对象
     */
    Flow insert(Flow flow);

    /**
     * 修改数据
     *
     * @param flow 实例对象
     * @return 实例对象
     */
    Flow update(Flow flow);

    /**
     * 通过主键删除数据
     *
     * @param flowId 主键
     * @return 是否成功
     */
    boolean deleteById(Long flowId);

}
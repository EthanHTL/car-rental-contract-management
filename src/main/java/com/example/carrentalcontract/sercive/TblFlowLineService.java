package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.model.TblFlowLine;

import java.util.List;

/**
 * 流程线表(TblFlowLine)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface TblFlowLineService {

    /**
     * 通过ID查询单条数据
     *
     * @param flowLineId 主键
     * @return 实例对象
     */
    TblFlowLine queryById(Long flowLineId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblFlowLine> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    TblFlowLine insert(TblFlowLine tblFlowLine);

    /**
     * 修改数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    TblFlowLine update(TblFlowLine tblFlowLine);

    /**
     * 通过主键删除数据
     *
     * @param flowLineId 主键
     * @return 是否成功
     */
    boolean deleteById(Long flowLineId);

}
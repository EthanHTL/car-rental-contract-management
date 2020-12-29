package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.view.TblFlowLine;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 流程线表(TblFlowLine)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface TblFlowLineMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param flowLineId 主键
     * @return 实例对象
     */
    TblFlowLine queryById(Long flowLineId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblFlowLine> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tblFlowLine 实例对象
     * @return 对象列表
     */
    List<TblFlowLine> queryAll(TblFlowLine tblFlowLine);

    /**
     * 新增数据
     *
     * @param tblFlowLine 实例对象
     * @return 影响行数
     */
    int insert(TblFlowLine tblFlowLine);

    /**
     * 修改数据
     *
     * @param tblFlowLine 实例对象
     * @return 影响行数
     */
    int update(TblFlowLine tblFlowLine);

    /**
     * 通过主键删除数据
     *
     * @param flowLineId 主键
     * @return 影响行数
     */
    int deleteById(Long flowLineId);

}
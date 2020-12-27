package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.TblLeaveAudit;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 审批表(TblLeaveAudit)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface TblLeaveAuditDao {

    /**
     * 通过ID查询单条数据
     *
     * @param auditId 主键
     * @return 实例对象
     */
    TblLeaveAudit queryById(Long auditId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblLeaveAudit> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tblLeaveAudit 实例对象
     * @return 对象列表
     */
    List<TblLeaveAudit> queryAll(TblLeaveAudit tblLeaveAudit);

    /**
     * 新增数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 影响行数
     */
    int insert(TblLeaveAudit tblLeaveAudit);

    /**
     * 修改数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 影响行数
     */
    int update(TblLeaveAudit tblLeaveAudit);

    /**
     * 通过主键删除数据
     *
     * @param auditId 主键
     * @return 影响行数
     */
    int deleteById(Long auditId);

}
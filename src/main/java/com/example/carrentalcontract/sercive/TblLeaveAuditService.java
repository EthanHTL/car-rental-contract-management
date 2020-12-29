package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.view.TblLeaveAudit;

import java.util.List;

/**
 * 审批表(TblLeaveAudit)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface TblLeaveAuditService {

    /**
     * 通过ID查询单条数据
     *
     * @param auditId 主键
     * @return 实例对象
     */
    TblLeaveAudit queryById(Long auditId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TblLeaveAudit> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 实例对象
     */
    TblLeaveAudit insert(TblLeaveAudit tblLeaveAudit);

    /**
     * 修改数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 实例对象
     */
    TblLeaveAudit update(TblLeaveAudit tblLeaveAudit);

    /**
     * 通过主键删除数据
     *
     * @param auditId 主键
     * @return 是否成功
     */
    boolean deleteById(Long auditId);

}
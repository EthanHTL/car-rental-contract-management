package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.entity.model.TblLeaveAudit;
import com.example.carrentalcontract.mapper.TblLeaveAuditMapper;
import com.example.carrentalcontract.sercive.TblLeaveAuditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批表(TblLeaveAudit)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("tblLeaveAuditService")
public class TblLeaveAuditServiceImpl implements TblLeaveAuditService {
    @Resource
    private TblLeaveAuditMapper tblLeaveAuditDao;

    /**
     * 通过ID查询单条数据
     *
     * @param auditId 主键
     * @return 实例对象
     */
    @Override
    public TblLeaveAudit queryById(Long auditId) {
        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TblLeaveAudit> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 实例对象
     */
    @Override
    public TblLeaveAudit insert(TblLeaveAudit tblLeaveAudit) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param tblLeaveAudit 实例对象
     * @return 实例对象
     */
    @Override
    public TblLeaveAudit update(TblLeaveAudit tblLeaveAudit) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param auditId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long auditId) {
        return false;
    }
}
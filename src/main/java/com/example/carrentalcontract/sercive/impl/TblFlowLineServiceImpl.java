package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.entity.TblFlowLine;
import com.example.carrentalcontract.mapper.TblFlowLineDao;
import com.example.carrentalcontract.sercive.TblFlowLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程线表(TblFlowLine)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("tblFlowLineService")
public class TblFlowLineServiceImpl implements TblFlowLineService {
    @Resource
    private TblFlowLineDao tblFlowLineDao;

    /**
     * 通过ID查询单条数据
     *
     * @param flowLineId 主键
     * @return 实例对象
     */
    @Override
    public TblFlowLine queryById(Long flowLineId) {
        return this.tblFlowLineDao.queryById(flowLineId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TblFlowLine> queryAllByLimit(int offset, int limit) {
        return this.tblFlowLineDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowLine insert(TblFlowLine tblFlowLine) {
        this.tblFlowLineDao.insert(tblFlowLine);
        return tblFlowLine;
    }

    /**
     * 修改数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowLine update(TblFlowLine tblFlowLine) {
        this.tblFlowLineDao.update(tblFlowLine);
        return this.queryById(tblFlowLine.getFlowLineId());
    }

    /**
     * 通过主键删除数据
     *
     * @param flowLineId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long flowLineId) {
        return this.tblFlowLineDao.deleteById(flowLineId) > 0;
    }
}
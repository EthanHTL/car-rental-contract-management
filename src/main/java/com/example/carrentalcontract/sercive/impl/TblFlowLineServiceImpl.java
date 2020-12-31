package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.entity.view.TblFlowLine;
import com.example.carrentalcontract.mapper.TblFlowLineMapper;
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
    private TblFlowLineMapper tblFlowLineDao;

    /**
     * 通过ID查询单条数据
     *
     * @param flowLineId 主键
     * @return 实例对象
     */
    @Override
    public TblFlowLine queryById(Long flowLineId) {
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
    public List<TblFlowLine> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowLine insert(TblFlowLine tblFlowLine) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param tblFlowLine 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowLine update(TblFlowLine tblFlowLine) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param flowLineId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long flowLineId) {
        return false;
    }
}
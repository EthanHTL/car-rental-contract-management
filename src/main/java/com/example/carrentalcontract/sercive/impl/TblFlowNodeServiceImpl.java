package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.entity.view.TblFlowNode;
import com.example.carrentalcontract.mapper.TblFlowNodeMapper;
import com.example.carrentalcontract.sercive.TblFlowNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程节点表(TblFlowNode)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("tblFlowNodeService")
public class TblFlowNodeServiceImpl implements TblFlowNodeService {
    @Resource
    private TblFlowNodeMapper tblFlowNodeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param flowNodeId 主键
     * @return 实例对象
     */
    @Override
    public TblFlowNode queryById(Long flowNodeId) {
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
    public List<TblFlowNode> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param tblFlowNode 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowNode insert(TblFlowNode tblFlowNode) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param tblFlowNode 实例对象
     * @return 实例对象
     */
    @Override
    public TblFlowNode update(TblFlowNode tblFlowNode) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param flowNodeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long flowNodeId) {
        return false;
    }
}
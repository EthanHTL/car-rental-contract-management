package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.entity.model.Flow;
import com.example.carrentalcontract.mapper.FlowMapper;
import com.example.carrentalcontract.sercive.FlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程表(Flow)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("flowService")
public class FlowServiceImpl implements FlowService {
    @Resource
    private FlowMapper flowDao;

    /**
     * 通过ID查询单条数据
     *
     * @param flowId 主键
     * @return 实例对象
     */
    @Override
    public Flow queryById(Long flowId) {
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
    public List<Flow> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param flow 实例对象
     * @return 实例对象
     */
    @Override
    public Flow insert(Flow flow) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param flow 实例对象
     * @return 实例对象
     */
    @Override
    public Flow update(Flow flow) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param flowId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long flowId) {
        return false;
    }
}
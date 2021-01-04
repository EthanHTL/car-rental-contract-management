package com.example.carrentalcontract.controller;

import com.example.carrentalcontract.entity.model.TblFlowNode;
import com.example.carrentalcontract.sercive.TblFlowNodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 流程节点表(TblFlowNode)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("tblFlowNode")
public class TblFlowNodeController {
    /**
     * 服务对象
     */
    @Resource
    private TblFlowNodeService tblFlowNodeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TblFlowNode selectOne(Long id) {
        return this.tblFlowNodeService.queryById(id);
    }

}
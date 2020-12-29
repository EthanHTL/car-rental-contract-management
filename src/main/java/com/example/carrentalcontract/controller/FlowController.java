package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.view.Flow;
import com.example.carrentalcontract.sercive.FlowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 流程表(Flow)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("flow")
public class FlowController {
    /**
     * 服务对象
     */
    @Resource
    private FlowService flowService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Flow selectOne(Long id) {
        return this.flowService.queryById(id);
    }

}
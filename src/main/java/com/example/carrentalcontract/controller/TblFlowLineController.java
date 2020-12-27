package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.TblFlowLine;
import com.example.carrentalcontract.sercive.TblFlowLineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 流程线表(TblFlowLine)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("tblFlowLine")
public class TblFlowLineController {
    /**
     * 服务对象
     */
    @Resource
    private TblFlowLineService tblFlowLineService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TblFlowLine selectOne(Long id) {
        return this.tblFlowLineService.queryById(id);
    }

}
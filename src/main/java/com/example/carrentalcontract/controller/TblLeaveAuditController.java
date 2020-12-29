package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.view.TblLeaveAudit;
import com.example.carrentalcontract.sercive.TblLeaveAuditService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 审批表(TblLeaveAudit)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("tblLeaveAudit")
public class TblLeaveAuditController {
    /**
     * 服务对象
     */
    @Resource
    private TblLeaveAuditService tblLeaveAuditService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TblLeaveAudit selectOne(Long id) {
        return this.tblLeaveAuditService.queryById(id);
    }

}
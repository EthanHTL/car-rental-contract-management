package com.example.carrentalcontract.controller;

import com.example.carrentalcontract.entity.model.TblFlowRoleUser;
import com.example.carrentalcontract.sercive.TblFlowRoleUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 流程角色_员工表(TblFlowRoleUser)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("tblFlowRoleUser")
public class TblFlowRoleUserController {
    /**
     * 服务对象
     */
    @Resource
    private TblFlowRoleUserService tblFlowRoleUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TblFlowRoleUser selectOne(Long id) {
        return this.tblFlowRoleUserService.queryById(id);
    }

}
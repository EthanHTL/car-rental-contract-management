package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.UserContract;
import com.example.carrentalcontract.sercive.UserContractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户车辆合同管理表(UserContract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("userContract")
public class UserContractController {
    /**
     * 服务对象
     */
    @Resource
    private UserContractService userContractService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserContract selectOne(Long id) {
        return this.userContractService.queryById(id);
    }

}
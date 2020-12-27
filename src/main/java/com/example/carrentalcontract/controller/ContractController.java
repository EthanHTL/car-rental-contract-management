package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.Contract;
import com.example.carrentalcontract.sercive.ContractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 合同表(Contract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("contract")
public class ContractController {
    /**
     * 服务对象
     */
    @Resource
    private ContractService contractService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Contract selectOne(Long id) {
        return this.contractService.queryById(id);
    }

}
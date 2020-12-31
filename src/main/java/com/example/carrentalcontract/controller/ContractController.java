package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Contract;
import com.example.carrentalcontract.sercive.ContractService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 合同表(Contract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/contract")
public class ContractController {
    /**
     * 服务对象
     */
    @Resource
    private ContractService contractService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    // @GetMapping("/find/all")
    // public Result<List<Contract>> selectAll() {
    //     return this.contractService.selectAll();
    // }


    @PostMapping("/find/page")
    public Result<PageInfo<Contract>> findPage(@RequestBody Contract contract) {
        return this.contractService.findPage(contract);
    }


}
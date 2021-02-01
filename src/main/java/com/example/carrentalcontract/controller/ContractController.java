package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.example.carrentalcontract.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 合同表(Contract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/contract")
@Slf4j
public class ContractController {
    /**
     * 服务对象
     */
    @Resource
    private ContractService contractService;
    @Resource
    private SysResourceService sysResourceService;

    /**
     * 分页
     */
    @PostMapping("/find/page")
    public Result<PageInfo<Contract>> findPage(@RequestBody Contract contract) {
        return this.contractService.findPage(contract);
    }

    /**
     * 查询所有合同
     *
     */
    @PostMapping("/find/all")
    public Result<List<Contract>> findAll() {
        return this.contractService.findAll();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("files") MultipartFile[] files) {
        return sysResourceService.uploadFiles(files);
    }

    @PostMapping("/create")
    public Result createContract(@RequestBody Contract contract){
        Long uid = SessionUtil.getCurrentUser().getId();
        return contractService.createContract(contract,uid);
    }




}
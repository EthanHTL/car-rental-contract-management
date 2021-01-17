package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysResource;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/car/resource")
@Slf4j
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 查找合同模板分页
     */
    @PostMapping("/contract/template/find/page")
    public Result<PageInfo<SysResource>> findContractPage(@RequestBody SysResource sysResource){
        return sysResourceService.findContractTemplatePage(sysResource);
    }

    /**
     * 创建模板
     */
    @PostMapping("/contract/template/create")
    public Result createContract(@RequestBody SysResource sysResource){
        return sysResourceService.createContract(sysResource);
    }
    /**
     * 修改模板
     */
    @PostMapping("/contract/template/update")
    public Result updateContractTemplate(@RequestBody SysResource sysResource){
        return sysResourceService.updateContractTemplate(sysResource);
    }

}

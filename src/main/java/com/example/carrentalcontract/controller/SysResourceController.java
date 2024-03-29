package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysResource;
import com.example.carrentalcontract.entity.request.SysResourceRequest;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<PageInfo<SysResource>> findContractTemplatePage(@RequestBody SysResourceRequest sysResource){
        return sysResourceService.findContractTemplatePage(sysResource);
    }
    /**
     * 完整合同分页
     */
    @PostMapping("/contract/template/whole/find/page")
    public Result<PageInfo<SysResource>> findContractPage(@RequestBody SysResourceRequest sysResource){
        return sysResourceService.findContractWholeTemplatePage(sysResource);
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

    @PostMapping("/contract/template/delete")
    public Result deleteContractTemplate(@RequestBody SysResource sysResource){
        return sysResourceService.destroy(sysResource);
    }

    /**
     * 查找合同模板分页
     */
    @PostMapping("/find/page")
    public Result<PageInfo<SysResource>> findPage(@RequestBody SysResource sysResource){
        return sysResourceService.findPage(sysResource);
    }

    @PostMapping("/get")
    public Result<SysResource> get(@RequestBody SysResource sysResource){
        return sysResourceService.selectByPrimaryKey(sysResource.getId());
    }

    @PostMapping("/update")
    public Result<SysResource> updateResource(@RequestBody SysResource sysResource){
        return sysResourceService.updateResource(sysResource);
    }

    @PostMapping("/delete")
    public Result<SysResource> deleteResource(@RequestBody SysResource sysResource){
        return sysResourceService.deleteResource(sysResource);
    }

}

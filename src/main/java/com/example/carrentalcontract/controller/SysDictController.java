package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysDict;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.example.carrentalcontract.sercive.SysDictDetailService;
import com.example.carrentalcontract.sercive.SysDictService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典(DataDictionary)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/dictionary")
public class SysDictController {
    /**
     * 服务对象
     */
    @Resource
    private SysDictService sysDictService;
    @Resource
    private SysDictDetailService sysDictDetailService;

    @PostMapping("/find/all")
    public Result<List<SysDict>> findAll(){
        return sysDictService.findAll();
    }

    @PostMapping("/find/page")
    public Result<PageInfo<SysDict>> findPage(@RequestBody SysDict dictionary){
        return sysDictService.findPage(dictionary);
    }
    @PostMapping("get")
    public Result get(@RequestBody SysDict dictionary){
        return sysDictService.selectByPrimaryKey(dictionary.getId());
    }
    @PostMapping("/insert")
    public Result insert(@RequestBody SysDict dictionary){
        return sysDictService.insert(dictionary);
    }

    @PostMapping("/update")
    public Result update(@RequestBody SysDict dictionary){
        return sysDictService.update(dictionary);
    }

    @PostMapping("/destory")
    public Result destory(@RequestBody SysDict dictionary){
        return sysDictService.destory(dictionary);
    }

    // -------字典详情操作
    @NotNull(field = "code",name = "代码",statusCode = 601)
    @PostMapping("/find/code")
    public Result<List<SysDictDetail>> findDetailByCode(@RequestBody SysDict dict){
        return sysDictDetailService.findDetailByCode(dict);
    }
    @PostMapping("/detail/get")
    public Result getDetail(@RequestBody SysDictDetail detail){
        return sysDictDetailService.selectByPrimaryKey(detail.getId());
    }
    @PostMapping("/detail/insert")
    public Result insertDetail(@RequestBody SysDictDetail detail){
        return sysDictDetailService.insert(detail);
    }
    @PostMapping("/detail/update")
    public Result updateDetail(@RequestBody SysDictDetail detail){
        return sysDictDetailService.update(detail);
    }
    @PostMapping("/detail/destroy")
    public Result deleteDetail(@RequestBody SysDictDetail detail){
        return sysDictDetailService.destroy(detail);
    }

}
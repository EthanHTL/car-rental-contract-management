package com.example.carrentalcontract.controller;


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

    @PostMapping("/insert")
    public Result insert(SysDict dictionary){
        return sysDictService.insert(dictionary);
    }

    @PostMapping("/update")
    public Result update(SysDict dictionary){
        return sysDictService.update(dictionary);
    }

    @PostMapping("/delete")
    public Result delete(SysDict dictionary){
        return sysDictService.delete(dictionary);
    }

    @PostMapping("/destory")
    public Result destory(SysDict dictionary){
        return sysDictService.destory(dictionary);
    }

    @PostMapping("/find/code")
    public Result<List<SysDictDetail>> findDetailByCode(@RequestBody SysDict dict){
        return sysDictDetailService.findDetailByCode(dict);
    }

}
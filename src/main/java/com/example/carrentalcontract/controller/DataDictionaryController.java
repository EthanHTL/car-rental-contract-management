package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.entity.DataDictionary;
import com.example.carrentalcontract.sercive.DataDictionaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据字典(DataDictionary)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("dataDictionary")
public class DataDictionaryController {
    /**
     * 服务对象
     */
    @Resource
    private DataDictionaryService dataDictionaryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DataDictionary selectOne(Long id) {
        return this.dataDictionaryService.queryById(id);
    }

}
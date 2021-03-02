package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.UserMessage;
import com.example.carrentalcontract.sercive.UserMessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户车辆合同管理表(UserMessage)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("/api/v1/car/usermesssage")
public class UserMessageController {
    /**
     * 服务对象
     */
    @Resource
    private UserMessageService userMessageService;

    @PostMapping("/create")
    public Result create(@RequestBody UserMessage message) {
        return userMessageService.insert(message);
    }

    @PostMapping("/find/page")
    public Result<PageInfo<UserMessage>> findPage(@RequestBody UserMessage message) {
        return userMessageService.findPage(message);
    }

}
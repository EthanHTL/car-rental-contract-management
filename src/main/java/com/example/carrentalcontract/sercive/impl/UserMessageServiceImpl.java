package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.UserMessage;
import com.example.carrentalcontract.mapper.UserMessageMapper;
import com.example.carrentalcontract.sercive.UserMessageService;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户车辆合同管理表(UserMessage)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("userMessageService")
public class UserMessageServiceImpl extends DbServiceImpl<UserMessage> implements UserMessageService {
    @Resource
    private UserMessageMapper messageMapper;

    @Override
    public Result<UserMessage> insert(UserMessage userMessage) {
        return super.insert(userMessage);
    }

    @Override
    public Result<PageInfo<UserMessage>> findPage(UserMessage message) {
        return super.selectPage(message, message.getPageNum(), message.getPageSize());
    }
}
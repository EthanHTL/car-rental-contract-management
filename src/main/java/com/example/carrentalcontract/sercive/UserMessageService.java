package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.UserMessage;
import com.github.pagehelper.PageInfo;

/**
 * 用户车辆合同管理表(UserMessage)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UserMessageService extends DbService<UserMessage> {

    @Override
    Result<UserMessage> insert( UserMessage userMessage);

    Result<PageInfo<UserMessage>> findPage(UserMessage message);
}
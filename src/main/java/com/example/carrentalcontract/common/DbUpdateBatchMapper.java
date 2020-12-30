package com.example.carrentalcontract.common;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.Map;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-30 16:51
 **/
@RegisterMapper
public interface DbUpdateBatchMapper<T> {
    @UpdateProvider(
            type = DbUpdateBatchExecutor.class,
            method = "dynamicSQL"
    )
    int updateBatch(Map<String, Object> map);
}
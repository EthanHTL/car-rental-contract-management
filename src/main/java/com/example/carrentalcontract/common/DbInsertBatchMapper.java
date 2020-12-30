package com.example.carrentalcontract.common;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-30 16:43
 **/
@RegisterMapper
public interface DbInsertBatchMapper<T> {
    @InsertProvider(
            type = DbInsertBatchExecutor.class,
            method = "dynamicSQL"
    )
    int insertBatch(List<T> list);
}

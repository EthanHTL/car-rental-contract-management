package com.example.carrentalcontract.common;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface DbMapper<T> extends Mapper<T>, DbInsertBatchMapper<T>, DbUpdateBatchMapper<T> {
}

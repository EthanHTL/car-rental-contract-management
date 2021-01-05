package com.example.carrentalcontract.common;

import tk.mybatis.mapper.common.Mapper;

public interface DbMapper<T> extends Mapper<T>, DbInsertBatchMapper<T>, DbUpdateBatchMapper<T> {

}

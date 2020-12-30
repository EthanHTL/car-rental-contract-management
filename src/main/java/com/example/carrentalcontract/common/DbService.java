package com.example.carrentalcontract.common;

import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-30 16:52
 **/
public interface DbService<T> {
    Result<T> insert(@NonNull T t);

    Result<Long> insertOne(@NonNull T t);

    Result insertBatch(@NonNull List<T> list);

    Result update(@NonNull T t);

    Result updateByExample(@NonNull T t, @NonNull Example example);

    Result delete(@NonNull T t);

    Result destroy(@NonNull T t);

    Result<T> selectOne(@NonNull T query);

    Result<T> selectByPrimaryKey(@NonNull Long id);

    Result<Integer> selectCount(T query);

    Result<Integer> selectCountByExample(Weekend<T> weekend);

    Result<List<T>> selectAll();

    Result<List<T>> select(@NonNull T query);

    Result<List<T>> select(@NonNull T query, @NonNull String orderField);

    Result<List<T>> select(Example example);

    Result<PageInfo<T>> selectPage(@NonNull T query, @NonNull int pageNum, @NonNull int pageSize);

    Result<PageInfo<T>> selectPage(@NonNull T query, @NonNull int pageNum, @NonNull int pageSize, @NonNull String orderField);

    Result<PageInfo<T>> selectPage(@NonNull Weekend<T> weekend, @NonNull int pageNum, @NonNull int pageSize);

    Result<PageInfo<T>> selectPage(@NonNull Weekend<T> weekend, @NonNull int pageNum, @NonNull int pageSize, @NonNull String orderField, @NonNull boolean isDesc);

    Result<List<T>> selectIn(Class<T> clazz, Collection<Long> idList);

    Result<List<T>> selectIn(Class<T> clazz, List<Long> idList);

    Result<List<T>> selectIn(Collection<Long> idList);

    Result<List<T>> selectNotIn(Class<T> clazz, Collection<Long> idList);

    Result<List<T>> selectNotIn(Class<T> clazz, List<Long> idList);

    Result<List<T>> selectNotIn(Collection<Long> idList);
}

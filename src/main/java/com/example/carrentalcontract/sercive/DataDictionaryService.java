package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.DataDictionary;

import java.util.List;

/**
 * 数据字典(DataDictionary)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface DataDictionaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param typeCode 主键
     * @return 实例对象
     */
    DataDictionary queryById(Long typeCode);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DataDictionary> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dataDictionary 实例对象
     * @return 实例对象
     */
    DataDictionary insert(DataDictionary dataDictionary);

    /**
     * 修改数据
     *
     * @param dataDictionary 实例对象
     * @return 实例对象
     */
    DataDictionary update(DataDictionary dataDictionary);

    /**
     * 通过主键删除数据
     *
     * @param typeCode 主键
     * @return 是否成功
     */
    boolean deleteById(Long typeCode);

}
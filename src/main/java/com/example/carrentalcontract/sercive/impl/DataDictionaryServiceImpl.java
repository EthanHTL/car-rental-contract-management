package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.entity.view.DataDictionary;
import com.example.carrentalcontract.mapper.DataDictionaryMapper;
import com.example.carrentalcontract.sercive.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典(DataDictionary)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param typeCode 主键
     * @return 实例对象
     */
    @Override
    public DataDictionary queryById(Long typeCode) {
        return this.dataDictionaryMapper.queryById(typeCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DataDictionary> queryAllByLimit(int offset, int limit) {
        return this.dataDictionaryMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dataDictionary 实例对象
     * @return 实例对象
     */
    @Override
    public DataDictionary insert(DataDictionary dataDictionary) {
        this.dataDictionaryMapper.insert(dataDictionary);
        return dataDictionary;
    }

    /**
     * 修改数据
     *
     * @param dataDictionary 实例对象
     * @return 实例对象
     */
    @Override
    public DataDictionary update(DataDictionary dataDictionary) {
        this.dataDictionaryMapper.update(dataDictionary);
        return this.queryById(dataDictionary.getTypeCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param typeCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long typeCode) {
        return this.dataDictionaryMapper.deleteById(typeCode) > 0;
    }
}
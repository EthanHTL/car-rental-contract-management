package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.entity.view.DataDictionary;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 数据字典(DataDictionary)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface DataDictionaryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param typeCode 主键
     * @return 实例对象
     */
    DataDictionary queryById(Long typeCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DataDictionary> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dataDictionary 实例对象
     * @return 对象列表
     */
    List<DataDictionary> queryAll(DataDictionary dataDictionary);

    /**
     * 新增数据
     *
     * @param dataDictionary 实例对象
     * @return 影响行数
     */
    int insert(DataDictionary dataDictionary);

    /**
     * 修改数据
     *
     * @param dataDictionary 实例对象
     * @return 影响行数
     */
    int update(DataDictionary dataDictionary);

    /**
     * 通过主键删除数据
     *
     * @param typeCode 主键
     * @return 影响行数
     */
    int deleteById(Long typeCode);

}
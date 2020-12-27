package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.Contract;

import java.util.List;

/**
 * 合同表(Contract)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:54
 */
public interface ContractService {

    /**
     * 通过ID查询单条数据
     *
     * @param contractId 主键
     * @return 实例对象
     */
    Contract queryById(Long contractId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Contract> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contract 实例对象
     * @return 实例对象
     */
    Contract insert(Contract contract);

    /**
     * 修改数据
     *
     * @param contract 实例对象
     * @return 实例对象
     */
    Contract update(Contract contract);

    /**
     * 通过主键删除数据
     *
     * @param contractId 主键
     * @return 是否成功
     */
    boolean deleteById(Long contractId);

}
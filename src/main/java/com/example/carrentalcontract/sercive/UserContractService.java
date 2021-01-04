package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.entity.model.UserContract;

import java.util.List;

/**
 * 用户车辆合同管理表(UserContract)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UserContractService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserContract queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserContract> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userContract 实例对象
     * @return 实例对象
     */
    UserContract insert(UserContract userContract);

    /**
     * 修改数据
     *
     * @param userContract 实例对象
     * @return 实例对象
     */
    UserContract update(UserContract userContract);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
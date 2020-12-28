package com.example.carrentalcontract.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.example.carrentalcontract.entity.Contract;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 合同表(Contract)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:53
 */
public interface ContractDao extends Mapper<Contract> {


    List<Contract> selectAll();
    Page<Contract> all();
}
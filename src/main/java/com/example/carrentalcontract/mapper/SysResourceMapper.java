package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper extends DbMapper<SysResource> {

    List<SysResource> findContractTemplatePage(SysResource sysResource);

    List<SysResource> findContractPage(@Param("res") SysResource sysResource,Integer pageNum, Integer pageSize);

}

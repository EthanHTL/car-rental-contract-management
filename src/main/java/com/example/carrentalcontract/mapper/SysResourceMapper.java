package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysResource;

import java.util.List;

public interface SysResourceMapper extends DbMapper<SysResource> {

    List<SysResource> findContractPage(SysResource sysResource);

}

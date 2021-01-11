package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.entity.model.SysRole;

import java.util.List;

public interface SysApiMapper extends DbMapper<SysApi> {

    List<SysApi> findSysApisByRolesIds(List<SysRole> roles);
}

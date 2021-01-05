package com.example.carrentalcontract.mapper;

import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysMenuMapper extends DbMapper<SysMenu> {

    List<SysMenu> findSysMenuByRoleofIds(List<SysRole> roles);
}

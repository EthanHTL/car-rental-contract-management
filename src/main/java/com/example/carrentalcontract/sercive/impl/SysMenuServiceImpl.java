package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.mapper.SysMenuMapper;
import com.example.carrentalcontract.sercive.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 菜单
 * @author: 黄天亮
 * @create: 2021-01-05 17:06
 **/
public class SysMenuServiceImpl extends DbServiceImpl<SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

}

package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.entity.model.SysMenu;

import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-07 11:24
 **/
public class SysMenuView extends SysMenu {

    // 子菜单
    private List<SysMenu> childrenList;
}

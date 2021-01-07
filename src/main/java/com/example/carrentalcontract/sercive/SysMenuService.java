package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.view.SysMenuView;
import lombok.NonNull;

import java.util.List;

public interface SysMenuService extends DbService<SysMenu> {

    Result update(SysMenu sysMenu);

    Result<List<SysMenu>> selectAll(SysMenu sysMenu);

    Result delete(SysMenu sysMenu);

    Result destroy(SysMenu sysMenu);

    Result<List<SysMenuView>> selectMenusByRole(SysRole role);

    Result save(List<SysMenuView> menus);
}

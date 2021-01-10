package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.request.SysRoleRequest;
import com.example.carrentalcontract.sercive.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(SysRole)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/role/")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @PostMapping("/find/all")
    public Result<List<SysRole>> findAll() {
        return this.roleService.findAll();
    }

    @PostMapping("/find/role/security")
    public Result<SysRoleRequest> findSecurity(SysRole role) {
        return this.roleService.findSecurity(role);
    }


}
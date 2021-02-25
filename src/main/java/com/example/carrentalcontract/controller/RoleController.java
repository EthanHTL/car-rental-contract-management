package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.SysUserRequest;
import com.example.carrentalcontract.entity.response.SysApiResponseInfo;
import com.example.carrentalcontract.entity.response.SysMenuResponseInfo;
import com.example.carrentalcontract.entity.response.SysRoleResponseInfo;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.SysApiService;
import com.example.carrentalcontract.sercive.SysMenuService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/api/v1/car")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;
    @Resource
    private SysApiService sysApiService;
    @Resource
    private SysMenuService sysMenuService;

    // --------role
    @PostMapping("/role/find/all")
    public Result<List<SysRole>> findAll() {
        return this.roleService.findAll();
    }

    @PostMapping("/role/assign")
    public Result assignRoleForUser(@RequestBody SysUserRequest userRequest){
        return this.roleService.insertUserRole(userRequest);
    }

    @PostMapping("/role/user/get")
    public Result<List<SysRole>> findUserRoles(@RequestBody SysUser user){
        return this.roleService.findRolesByUser(user);
    }

    @PostMapping("/role/security/find/all")
    public Result<SysRoleResponseInfo> findAllSecurity() {
        return this.roleService.findAllSecurity();
    }

    @PostMapping("/role/security/find")
    public Result<SysRoleResponseInfo> findSecurity(@RequestBody List<SysRole> roles) {
        return this.roleService.findSecurityByRoles(roles);
    }

    @PostMapping("/role/insert")
    public Result roleInsert(@RequestBody SysRole role) {
        return this.roleService.insert(role);
    }

    @PostMapping("/role/update")
    public Result roleUpdate(@RequestBody SysRole role) {
        return this.roleService.update(role);
    }
    @PostMapping("/role/destroy")
    public Result roleDestroy(@RequestBody SysRole role) {
        return this.roleService.deleteRole(role);
    }

    // 给角色分配权限
    @PostMapping("/role/permissions/assign")
    public Result<SysRoleResponseInfo> assign(@RequestBody SysRoleResponseInfo roleResponseInfo) {
        return this.roleService.assignPermission(roleResponseInfo);
    }

    // --------menu


    @PostMapping("/menu/find/tree")
    public Result<List<SysMenuResponseInfo>> findMenuTree() {
        return this.roleService.findMenuTree();
    }

    @PostMapping("/menu/find/all")
    public Result<List<SysMenu>> findMenuAll() {
        return this.sysMenuService.selectAll();
    }

    @PostMapping("/menu/create")
    public Result createMenu(@RequestBody SysMenu menu) {
        return sysMenuService.insert(menu);
    }
    @PostMapping("/menu/update")
    public Result updateMenu(@RequestBody SysMenu menu) {
        return sysMenuService.update(menu);
    }
    @PostMapping("/menu/delete")
    public Result deleteMenu(@RequestBody SysMenu menu) {
        return sysMenuService.destroy(menu);
    }

    // --------api

    @PostMapping("/api/find/tree")
    public Result<List<SysApiResponseInfo>> findApiTree() {
        return this.roleService.findApiTree();
    }

    @PostMapping("/api/find/all")
    public Result<List<SysApi>> findApiAll() {
        return this.sysApiService.selectAll();
    }

    @PostMapping("/api/find/page")
    public Result<PageInfo<SysApi>> findApiAll(@RequestBody SysApi sysApi) {
        return sysApiService.findPage(sysApi);
    }

    @PostMapping("/api/create")
    public Result createApi(@RequestBody SysApi sysApi) {
        return sysApiService.insert(sysApi);
    }
    @PostMapping("/api/update")
    public Result updateApi(@RequestBody SysApi sysApi) {
        return sysApiService.update(sysApi);
    }
    @PostMapping("/api/delete")
    public Result deleteApi(@RequestBody SysApi sysApi) {
        return sysApiService.destroy(sysApi);
    }


}
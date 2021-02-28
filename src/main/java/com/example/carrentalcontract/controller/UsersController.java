package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.SysMenuService;
import com.example.carrentalcontract.sercive.UsersService;
import com.example.carrentalcontract.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@RestController
@RequestMapping("/api/v1/car/users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;
    @Autowired
    private RoleService roleService;

    /**
     * 注册
     *
     * @param user user
     */
    @NotNull(field = "username", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/register")
    public Result register(@RequestBody SysUser user) {
        return usersService.insert(user);
    }

    @NotNull(field = "username", name = "账号", statusCode = 701)
    @PostMapping("/check/username")
    public Result<Boolean> checkUsername(@RequestBody SysUser user) {
        return usersService.selectByUsername(user.getUsername());
    }
    /**
     * 查询所有用户
     *
     * @param user 用户
     */
    @NotNull(field = "id", name = "主键", statusCode = 701)
    @NotNull(field = "username", name = "账户", statusCode = 702)
    @PostMapping("/update/password")
    public Result updatePassword(@RequestBody SysUser user) {
        return usersService.updatePassword(user);
    }


    /**
     * 跟新操作
     *
     * @param user 用户
     */
    @NotNull(field = "id", name = "主键", statusCode = 701)
    @PostMapping("/update")
    public Result update(@RequestBody SysUser user) {
        return usersService.update(user);
    }

    @PostMapping("/find/password")
    public Result<Boolean> getOldPassword() {
        return usersService.selectByUsername(getUserInfo().getUsername());
    }

    @PostMapping("/find/menu")
    public Result findUserMenu() {
        SysUser user = SessionUtil.getCurrentUser();
        return roleService.findUserMenu(user);
    }



    public UserDetails getUserInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
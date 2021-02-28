package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.UserEnum;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.SysUserRequest;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.UsersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Service("usersService")
public class UsersServiceImpl extends DbServiceImpl<SysUser> implements UsersService {

    @Resource
    UsersMapper userMapper;

    @Autowired
    RoleService roleService;



    @Override
    @Transactional
    public Result insert(SysUser user) {
        // 账号查重
        if (checkUserCode(user)) {
            return new Result(UserEnum.USER_READY);
        }
        // 密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String bsPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bsPassword);

        Result<SysUser> userResult = super.insert(user);
        // 设置用户角色
        SysUserRequest userRequest = new SysUserRequest();
        SysRole role = new SysRole();
        role.setId(1L);
        List<SysRole> roles = new ArrayList<>();
        roles.add(role);
        userRequest.setRoleList(roles).setId(userResult.getData().getId());
        // 分配角色
        return roleService.insertUserRole(userRequest);
    }

    @Override
    public Result update(SysUser sysUser) {
        if (!checkUserCode(sysUser)) {
            return new  Result(901,"账户不存在");
        }
        return super.update(sysUser);

    }

    @Override
    public Result<Boolean> selectByUsername(String username) {
        Weekend<SysUser> weekend = new Weekend<>(SysUser.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("username", username);
        List<SysUser> data = select(weekend).getData();
        return Result.success(data.size() > 0);
    }

    @Override
    public Result<PageInfo<SysUser>> findCustomerPage(SysUser user) {
        List<SysUser> employeePage = userMapper.findCustomerAll(user);
        PageInfo info = new PageInfo(employeePage);
        return Result.success(info);
    }

    @Override
    public Result<SysUser> login(SysUser user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Weekend<SysUser> weekend = new Weekend<>(SysUser.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("username",user.getUsername());
        List<SysUser> data = super.select(weekend).getData();
        if (data!=null&&data.size()>0){
            SysUser user1 = data.get(0);
            boolean matches = passwordEncoder.matches(user.getPassword(),user1.getPassword());
            if (matches){
                return Result.success(user1);
            }
        }
        return new Result<>(901,"账户密码错误");
    }

    @Override
    public Result<PageInfo<SysUser>> findEmployeePage(SysUser employee) {
        List<SysUser> employeePage = userMapper.findEmployeeAll(employee);
        PageInfo info = new PageInfo(employeePage);
        return Result.success(info);
    }

    @Override
    public Result<List<SysUser>> findEmployeeAll() {

        return Result.success();
    }

    @Override
    public Result updatePassword(SysUser user) {
        return super.update(user);
    }


    private boolean checkUserCode(SysUser user) {
        Weekend<SysUser> weekend = new Weekend<>(SysUser.class);
        Example.Criteria criteria = weekend.createCriteria();
        criteria.andEqualTo("username", user.getUsername());


        List<SysUser> data = select(weekend).getData();
        return data.size() > 0;
    }
}
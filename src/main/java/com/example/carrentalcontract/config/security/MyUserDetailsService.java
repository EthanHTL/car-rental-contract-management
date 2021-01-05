package com.example.carrentalcontract.config.security;

import com.example.carrentalcontract.entity.en.UserEnum;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.MyUserDetails;
import com.example.carrentalcontract.mapper.RoleMapper;
import com.example.carrentalcontract.mapper.SysApiMapper;
import com.example.carrentalcontract.mapper.SysMenuMapper;
import com.example.carrentalcontract.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 实现动态的将用户信息和权限从数据库加载
 * @author: 黄天亮
 * @create: 2021-01-05 11:32
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    UsersMapper usersMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    SysMenuMapper sysMenuMapper;
    @Resource
    SysApiMapper sysApiMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser loadUser = new SysUser();
        loadUser.setUsername(username);

        // 加载用户信息
        SysUser user = usersMapper.selectOne(loadUser);

        if (user == null) {// 数据库没有用户名，认证失败
            throw new UsernameNotFoundException(UserEnum.ACCOUNT_NOT_EXIST.getMessage());
        }
        // 将查询到的用户添加到 UserDetails 中
        MyUserDetails myUserDetails = new MyUserDetails(user);
        myUserDetails.setEnabled(true);
        // 权限列表
        List<String> authorities = new ArrayList<>();

        // 获取用户角色列表
        List<SysRole> roles = roleMapper.findRoleByUserName(username);
        roles.forEach(rc -> authorities.add("ROLE_" + rc.getRoleName()));

        // 根据角色列表加载当前用户具有的权限
        List<SysMenu> menus = sysMenuMapper.findSysMenuByRoleofIds(roles);
        menus.forEach(menu -> authorities.add(menu.getUrl()));

        List<SysApi> apis = sysApiMapper.findSysApisByRolesIds(roles);
        apis.forEach(api -> authorities.add(api.getUrl()));

        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));

        return myUserDetails;
    }
}

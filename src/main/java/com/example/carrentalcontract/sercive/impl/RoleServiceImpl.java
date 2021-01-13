package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.common.translate.ObjectMapper;
import com.example.carrentalcontract.entity.model.SysApi;
import com.example.carrentalcontract.entity.model.SysMenu;
import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.response.SysApiResponseInfo;
import com.example.carrentalcontract.entity.response.SysMenuResponseInfo;
import com.example.carrentalcontract.entity.response.SysRoleResponseInfo;
import com.example.carrentalcontract.entity.request.SysUserRequest;
import com.example.carrentalcontract.mapper.RoleMapper;
import com.example.carrentalcontract.mapper.SysApiMapper;
import com.example.carrentalcontract.mapper.SysMenuMapper;
import com.example.carrentalcontract.sercive.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Service("roleService")
public class RoleServiceImpl extends DbServiceImpl<SysRole> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysApiMapper sysApiMapper;

    @Override
    public Result<List<SysRole>> findAll() {

        // return Result.success(roleMapper.findRoleByUserName("111"));
        return super.selectAll();
    }

    @Override
    public Result insert(SysRole role) {
        return super.insertOne(role);
    }

    @Override
    public Result insertUserRole(SysUserRequest user) {
        return Result.success(roleMapper.insertUserAndRole(user.getRoleList(),user.getId()));
    }

    @Override
    public Result<SysRoleResponseInfo> findAllSecurity() {
        SysRoleResponseInfo roleResponseInfo = new SysRoleResponseInfo();

        List<SysMenu> sysMenus = sysMenuMapper.selectAll();
        List<SysMenuResponseInfo> menuResponseInfos = ObjectMapper.clone(sysMenus,SysMenuResponseInfo.class);
        List<SysApi> sysApis = sysApiMapper.selectAll();
        List<SysApiResponseInfo> apiResponseInfos = ObjectMapper.clone(sysApis,SysApiResponseInfo.class);

        List<SysMenuResponseInfo> menus = getMenuChildren(0L,menuResponseInfos);
        List<SysApiResponseInfo> apis = getApiChildren(0L,apiResponseInfos);
        roleResponseInfo.setMenuResponseInfos(menus);
        roleResponseInfo.setApiResponseInfos(apis);

        return Result.success(roleResponseInfo);
    }

    private List<SysMenuResponseInfo> getMenuChildren(Long parentId, List<SysMenuResponseInfo> menuResponseInfos) {
        List<SysMenuResponseInfo> children = menuResponseInfos.stream().filter(item -> {
            if (item.getMenuPid() == parentId) {
                item.setChildren(getMenuChildren(item.getId(),menuResponseInfos));
                return true;
            };
            return false;
        }).collect(Collectors.toList());;
        Collections.sort(children, Comparator.comparing(SysMenuResponseInfo::getSort));

        return children;
    }

    private List<SysApiResponseInfo> getApiChildren(Long parentId, List<SysApiResponseInfo> apiResponseInfos) {
        List<SysApiResponseInfo> children = apiResponseInfos.stream().filter(item -> {
            if (item.getApiPid() == parentId) {
                item.setChildren(getApiChildren(item.getId(),apiResponseInfos));
                return true;
            };
            return false;
        }).collect(Collectors.toList());;
        Collections.sort(children, Comparator.comparing(SysApiResponseInfo::getSort));

        return children;
    }

    @Override
    public Result<List<SysRoleResponseInfo>> findSecurityByRoles(List<SysRole> roles) {
        SysRoleResponseInfo roleResponseInfo = new SysRoleResponseInfo();
        List<SysMenuResponseInfo> menuResponseInfos = new ArrayList<>();
        List<SysApiResponseInfo> apiResponseInfos = new ArrayList<>();

        List<SysApi> sysApis = sysApiMapper.findSysApisByRolesIds(roles);
        List<SysMenu> sysMenus = sysMenuMapper.findSysMenuByRoleIds(roles);




        return null;
    }

    @Override
    public Result<SysRole> selectByPrimaryKey(Long id) {

        return super.selectByPrimaryKey(id);
    }


}
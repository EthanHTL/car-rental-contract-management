package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.exception.NotNullException;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.util.SessionUtil;
import com.example.carrentalcontract.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-02 17:16
 **/
@Slf4j
@Component
public class GroupTaskListenerImpl implements TaskListener {

    /**
     * 指定个人任务和组任务的办理人
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        RoleService roleService = SpringContextUtil.getObject(RoleService.class);
        String id = delegateTask.getTaskDefinitionKey();
        SysRole role = new SysRole();
        List<SysUser> users = new ArrayList<>();
        if (id.equals("_3")) {
            //
            role.setRoleName("employee");
        }else if (id.equals("_4")) {
            //    经理审批
            role.setRoleName("manager");
        } else if (id.equals("_5")) {
            //    总经理审批
            role.setRoleName("loader");
        }
        // 查询需要分配的代理人
        users = getUsers(role);
        if (users.size() == 0){
            log.warn("角色：{}，任务代理人为空！",role.getRoleName());
            throw new NotNullException(901,"任务代理人为空");
        }
        List<String> userList = users.stream().map(SysUser::getUsername).collect(Collectors.toList());
        log.info("分配代理人，users:{}",userList);
        delegateTask.addCandidateUsers(userList);
    }

    private List<SysUser> getUsers(SysRole role) {
        RoleService roleService = SpringContextUtil.getObject(RoleService.class);
        List<SysUser> users = new ArrayList<>();
        if (role.getRoleName().equals("employee")){
            SysUser user = SessionUtil.getCurrentUser();
            Set<String> userRoles = roleService.findRolesByUser(user).getData()
                    .stream().map(SysRole::getRoleName).collect(Collectors.toSet());
            if (userRoles.contains("employee")){
                users.add(user);
            }else {
                users = roleService.findUsersByRole(role).getData();
            }
        }else {
            users = roleService.findUsersByRole(role).getData();
        }
        return users;
    }
}

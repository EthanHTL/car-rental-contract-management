package com.example.carrentalcontract.listener.activiti;

import com.example.carrentalcontract.entity.model.SysRole;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.RoleService;
import com.example.carrentalcontract.sercive.UsersService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-02 17:16
 **/
public class GroupTaskListenerImpl implements TaskListener {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleService roleService;

    /**
     * 指定个人任务和组任务的办理人
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        String id = delegateTask.getId();
        SysRole role = new SysRole();
        List<SysUser> users = new ArrayList<>();
        if (id.equals("_4")) {
            //    经理审批
            role.setRoleName("manager");
        } else if (id.equals("_5")) {
            //    总经理审批
            role.setRoleName("loader");
        }
        users = roleService.findUsersByRole(role).getData();
        users.forEach(item ->{
            delegateTask.addCandidateUser(item.getId().toString());
        });
    }
}

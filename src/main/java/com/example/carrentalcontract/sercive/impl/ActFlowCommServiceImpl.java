package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.sercive.ActFlowCommService;
import com.example.carrentalcontract.sercive.UsersService;
import lombok.extern.slf4j.Slf4j;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ActFlowCommServiceImpl implements ActFlowCommService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UsersService usersService;

    @Override
    public Result saveNewDeploy() {
        return null;
    }

    @Override
    public ProcessInstance startProcess(String formKey, String beanName, String businessKey,
                                        Long id, Map<String, Object> variables) {
        // 修改业务状态

        variables.put("businessKey", businessKey);
        // 启动流程
        log.info("【启动流程】，formKey : {},businessKey : {}", formKey, businessKey);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(formKey, businessKey, variables);

        String definitionId = processInstance.getProcessDefinitionId();
        log.info("【启动流程】，成功,definitionId : {}", definitionId);
        return processInstance;
        /*
        【启动流程】，formKey : contract,businessKey : contract:1102010319513959

        【启动流程】，成功,definitionId : contract:1:5003
          processDefinitionId is contract:1:5003
         */
    }

    @Override
    public List<TaskInfo> myTaskList(String username) {
        /*
          根据负责人id 查询任务
         */
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(username);

        List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().list();

        List<TaskInfo> infos = new ArrayList<>();
        translateTaskList(taskList, infos);
        return infos;
    }

    @Override
    public List<TaskInfo> myGTaskList(String username) {

        List<Task> list = taskService.createTaskQuery()
                // .processDefinitionKey("contract")
                .taskCandidateUser(username)
                .list();
        List<TaskInfo> infos = new ArrayList<>();
        translateTaskList(list, infos);
        log.info("我的组任务：{}",infos);
        return infos;
    }

    private void translateTaskList(List<Task> taskList, List<TaskInfo> infos) {
        for (Task task : taskList) {
            TaskInfo info = new TaskInfo();
            info.setTaskId(task.getId());
            info.setTaskName(task.getName());
            info.setDescription(task.getDescription());
            info.setPriority(task.getPriority());
            info.setOwner(task.getOwner());
            info.setAssigneeUsername(task.getAssignee());
            info.setDelegationState(task.getDelegationState());
            info.setProcessInstanceId(task.getProcessInstanceId());
            info.setExecutionId(task.getExecutionId());
            info.setProcessDefinitionId(task.getProcessDefinitionId());
            info.setCreateTime(task.getCreateTime());
            info.setDueDate(task.getDueDate());
            info.setCategory(task.getCategory());
            info.setBusinessKey(task.getBusinessKey());

            if (task.getAssignee() != null) {
                SysUser user = new SysUser();
                user.setUsername(task.getAssignee());
                SysUser sysUser = usersService.selectOne(user).getData();
                info.setAssignee(sysUser.getUsername());
            }
            infos.add(info);
        }
    }

    @Override
    public Result completeProcess(String remark, String taskId, String userId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(userId)
                .singleResult();
        if (task == null) {
            log.error("completeProcess - task is null");
            return new Result(901, "任务不存在");
        }
        log.info("------完成任务操作  开始 ------");
        String processInstanceId = task.getProcessInstanceId();
        // 设置审批人的userID
        Authentication.setAuthenticatedUserId(userId);
        // 添加记录
        taskService.addComment(taskId, processInstanceId, remark);
        log.info("流程实例ID：{}", task.getProcessInstanceId());
        log.info("任务ID：{}", task.getId());
        log.info("任务责任人：{}", task.getAssignee());
        log.info("任务名称：{}", task.getName());
        taskService.complete(task.getId());
        log.info("------完成任务操作  结束 ------");
        return Result.success();
    }

    /**
     * 设置局部流程变量
     *
     * @param taskId    任务id
     * @param variables 变量
     */
    @Override
    public void setLocalVariables(String taskId, Map<String, Object> variables) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            log.error("setLocalVariables - task is null");
            new Result(901, "任务不存在");
            return;
        }
        taskService.setVariablesLocal(taskId, variables);
        Result.success();
    }

    @Override
    public Result claimTask(String taskId, String username) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(username)
                .singleResult();
        //    拾取任务
        if (task != null) {
            taskService.claim(taskId, username);
            log.info("用户，username：{}，拾取任务：taskId-{}" ,username,taskId);
        }
        return Result.success();
    }

    @Override
    public Result assigneeToGroupTask(String taskId, String username) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(username)
                .singleResult();
        if (task != null){
            //    任务归还
            taskService.setAssignee(taskId,null);
            log.info("用户，username：{}，归还任务：taskId-{}" ,username,taskId);
        }
        return Result.success();
    }

    @Override
    public Result deployment(String name, String path,String imgPath) {
        log.info("-------- 流程部署 开始---------");
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .addClasspathResource(path)
                .addClasspathResource(imgPath)
                .deploy();
        //    4、输出部署信息
        log.info("流程部署ID={}", deploy.getId());
        log.info("流程部署名字={}", deploy.getName());
        log.info("-------- 流程部署 结束---------");

        return Result.success();
    }

    @Override
    public Result deleteDeployment(String deploymentId, Boolean cascade) {
        log.info("-------- 删除流程部署信息 开始---------");
        repositoryService.deleteDeployment(deploymentId,cascade);
        log.info("-------- 删除流程部署信息 结束---------");
        return Result.success();
    }


}

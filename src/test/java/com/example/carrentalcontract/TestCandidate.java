package com.example.carrentalcontract;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试候选人
 */
@Slf4j
@SpringBootTest
public class TestCandidate {

    /**
     * 测试流程部署
     */
    @Test
    public void testDeployment() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //    3、使用service进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("张三合同审批")
                .addClasspathResource("resources/bpmn/contract.bpmn20.xml")
                .deploy();
        //    4、输出部署信息
        log.info("流程部署ID={}", deploy.getId()); // 72501
        log.info("流程部署名字={}", deploy.getName());
    }

    /**
     * 启动流程  的时候设置流程变量
     * 设置流程变量 num
     * 设置任务责任人
     */
    @Test
    public void testStartProcess() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 定义流程key
        String key = "testCandidate";
        // 启动流程
        runtimeService.startProcessInstanceByKey(key);
    }

    /**
     * 查询候选人任务
     */
    @Test
    public void findCandidateUserTaskList() {
        // 流程定义的key
        String key = "contract";
        //    任务后选人
        String candidateUser = "manager1";
        //    获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // TaskService
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(candidateUser) // 根据候选人查询任务
                .list();
        for (Task task : tasks) {
            System.out.println("=================");
            System.out.println("流程实例Id：" + task.getProcessInstanceId());
            System.out.println("任务Id：" + task.getId());
            System.out.println("任务负责人Id：" + task.getAssignee());
        }
    }

    /**
     * 查询个人代办任务
     */
    @Test
    public void findPersonalTaskList() {
        //    1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取taskService
        TaskService taskService = processEngine.getTaskService();
        //    3、根据流程 key 和 任务负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("testCandidate")
                .taskAssignee("lisi")
                .list();
        //    4、输出
        for (Task task : tasks) {
            log.info("流程实例ID：{}", task.getProcessInstanceId());
            log.info("任务ID：{}", task.getId());
            log.info("任务责任人：{}", task.getAssignee());
            log.info("任务名称：{}", task.getName());
        }
    }

    /**
     * 候选人拾取任务
     */
    @Test
    public void claimTask() {
        //    获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // TaskService
        TaskService taskService = processEngine.getTaskService();
        //    当前任务的id
        String taskId = "85002";
        // 候选人
        String candidateUser = "lisi";
        //  查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(candidateUser)
                .singleResult();
        //    拾取任务
        if (task != null){
            taskService.claim(taskId,candidateUser);
            System.out.println("taskId-"+taskId+"-用户"+candidateUser+"-拾取任务");
        }

    }

    /**
     * 任务归还
     */
    @Test
    public void testAssigneeToGroupTask(){
        //    获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // TaskService
        TaskService taskService = processEngine.getTaskService();
        //    当前任务的id
        String taskId = "85002";
        // 候选人
        String assignee = "lisi";
        //  根据 key和负责人 查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();
        if (task != null){
            //    任务归还
            taskService.setAssignee(taskId,null);
            System.out.println("taskId-"+taskId+"-用户"+assignee+"-归还任务，完成");
        }
    }

    /**
     * 任务交接
     */
    @Test
    public void testAssigneeToCandidateUser(){
        //    获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // TaskService
        TaskService taskService = processEngine.getTaskService();
        //    当前任务的id
        String taskId = "85002";
        // 候选人
        String assignee = "lisi";
        // 任务候选人
        String candidateUser = "wangwu";


        //  根据 key和负责人 查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();

        if (task != null){
            //  任务交接
            taskService.setAssignee(taskId,candidateUser);
            System.out.println("taskId-"+taskId+"-用户"+assignee+"-任务交接，完成");
        }
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask() {
        String key = "testCandidate";
        String assignee = "lisi";
        // String assignee = "汤姆";
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee)
                .singleResult();

        if (task != null) {
            taskService.complete(task.getId());
            System.out.println(task.getId() + "==>完成任务");
        }
    }

}

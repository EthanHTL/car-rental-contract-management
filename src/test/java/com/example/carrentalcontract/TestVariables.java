package com.example.carrentalcontract;

import com.example.carrentalcontract.pojo.Evection;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class TestVariables {

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
                .name("出差申请流程-variables1")
                .addClasspathResource("bpmn/evection-global.bpmn20.xml")
                .deploy();
        //    4、输出部署信息
        log.info("流程部署ID={}", deploy.getId()); // 27501
        log.info("流程部署名字={}", deploy.getName());
    }

    /**
     * 启动流程
     */
    @Test
    public void testStartProcess(){
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2、获取 RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //  3.流程定义的key
        String key = "evection-global";
        Map<String,Object> variables = new HashMap<>();
        // 设置流程变量
        Evection ev = new Evection();
        // 设置出差日期
        ev.setNum(3d);
        variables.put("evection",ev);
        variables.put("assignee0","李四");
        variables.put("assignee1","王经理");
        variables.put("assignee2","张总经理");
        variables.put("assignee3","周财务");


        //  4.启动流程
        runtimeService.startProcessInstanceByKey(key,variables);
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        String key = "evection-global";
        String assignee = "张总经理";
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee)
                .singleResult();

        if (task!=null) {
            taskService.complete(task.getId());
            System.out.println("完成任务");
        }
    }



}

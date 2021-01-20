package com.example.carrentalcontract;


import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class TestAssigneeUel {

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
                .name("出差申请流程-uel")
                .addClasspathResource("bpmn/myEvection1.bpmn")
                .deploy();
        //    4、输出部署信息
        log.info("流程部署ID={}", deploy.getId()); // 27501
        log.info("流程部署名字={}", deploy.getName());
    }

    @Test
    public void startAssigneeUel() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2、获取 RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //  3、启动流程实例
        //    设置assignee的值，用来代替uel表达式
        Map<String,Object> assigneeMap = new HashMap<>();
        assigneeMap.put("assignee0","张三");
        assigneeMap.put("assignee1","李经理");
        assigneeMap.put("assignee2","王总经理");
        assigneeMap.put("assignee3","赵财务");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection1", assigneeMap);

    }

}

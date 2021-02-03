package com.example.carrentalcontract;

import com.example.carrentalcontract.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiSpringTest {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private RuntimeService runtimeService;
    @Resource
    private SecurityUtil securityUtil;


    /**
     * 查询流程定义
     */
    @Test
    public void findProcess() {
        securityUtil.logInAs("manager1");

        Page<ProcessDefinition> definitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("可用的流程定义总数：{}", definitionPage.getTotalItems());
        for (ProcessDefinition definition : definitionPage.getContent()) {
            System.out.println("-------------------------");
            log.info("流程定义内容：{}", definition);
            System.out.println("-------------------------");
        }
        /**
         * -------------------------
         * 2021-01-24 18:25:20.875  INFO 2108 --- [           main] c.e.c.ActivitiSpringTest
         * : 流程定义内容：ProcessDefinition{id='testDemo:1:7160fcb2-5e2e-11eb-8319-3ca0672e4b11',
         * name='testSpringDemo', key='testDemo', description='null', formKey='null', version=1}
         * -------------------------
         */

    }


    /**
     * 启动流程
     */
    @Test
    public void startProcess() {
        //    设置登陆用户
        securityUtil.logInAs("system");
        ProcessInstance testDemo = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("testDemo")
                .build());
        // org.activiti.engine.runtime.ProcessInstance instance = runtimeService.startProcessInstanceByKey("testDemo");

        log.info("流程实例的内容：{}", testDemo);
    }

    @Test
    public void doTask(){

    }
}

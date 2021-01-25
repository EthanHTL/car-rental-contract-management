package com.example.carrentalcontract;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ActivitiBusinessDemo {

    /**
     * 添加业务key，到activiti的表
     */
    @Test
    public void addBusinessKey() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取RepositoryService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //    3、启动流程的过程中，添加businesskey
        //        第一个参数：流程定义的key
        //        第二个参数：businessKey,存出差申请单的Id，就是1001
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("testDemo", "1001");
        System.out.println("businesskey==>" + instance.getBusinessKey());

    }

    /**
     * 全部流程批量 激活 或 挂起流程
     * suspend 暂停
     */
    @Test
    public void suspendAllProcessInstance() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //    3、查询流程定义，获取流程定义的查询对象
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("hello")
                .singleResult();
        //    4、获取当前流程定义的实例是否都是挂起状态
        boolean suspended = definition.isSuspended();
        //    5、获取流程定义的id
        String id = definition.getId();
        //    6、如果是挂起状态，改为激活状态
        if (suspended) {
            // 如果是挂起，可以执行激活的操作，参数1：流程定义id，参数2：是否激活，参数3：激活的时间
            repositoryService.activateProcessDefinitionById(id, true, null);
            System.out.println("流程定义id：" + definition + ",已激活");
        } else {
            // 7、如果是激活状态，改为挂起状态，参数1：流程定义id，参数2：是否暂停，参数3：暂停的时间
            repositoryService.suspendProcessDefinitionById(id, true, null);
            System.out.println("流程定义id：" + definition + ",已挂起");
        }
        /**
         * 结果分析 : 操作数据库表中 *_state_字段
         * 1：表示激活，2：表示挂起
         */
    }

    /**
     * 单个流程 激活 或 挂起流程
     */
    @Test
    public void suspendSingleProcessInstance() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //    3、通过RuntimeService获取流程实例对象
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("17501")
                .singleResult();

        //    4、获取当前流程实例的暂停状态
        boolean suspended = instance.isSuspended();
        //    5、获取流程定义的id
        String id = instance.getId();
        //    6、如果是挂起状态，改为激活状态
        if (suspended) {
            // 如果是挂起，可以执行激活的操作，参数1：流程实例id
            runtimeService.activateProcessInstanceById(id);
            System.out.println("流程实例id：" + instance + ",已激活");
        } else {
            // 7、如果是激活状态，改为挂起状态，参数1：流程实例id
            runtimeService.suspendProcessInstanceById(id);
            System.out.println("流程实例id：" + instance + ",已挂起");
        }
        /**
         * 结果分析 : 操作数据库表中 *_state_字段
         * 1：表示激活，2：表示挂起
         */
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //    2、获取 TaskService
        TaskService taskService = processEngine.getTaskService();
    //    3、使用TaskService 获取任务，参数：流程实例的id，负责人
        Task task = taskService.createTaskQuery()
                .processInstanceId("12501")
                .taskAssignee("jery")
                .singleResult();
        System.out.println("流程实例id==》"+ task.getProcessInstanceId());
        System.out.println("流程任务id==》"+ task.getId());
        System.out.println("负责人==》"+ task.getAssignee());
        System.out.println("任务名称==》"+ task.getName());
        /*
        流程实例id==》12501
        流程任务id==》15002
        负责人==》jery
        任务名称==》经理审批
         */
        taskService.complete(task.getId());

    }


}

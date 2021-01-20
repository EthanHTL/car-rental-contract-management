package com.example.carrentalcontract;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;


/**
 * @description: Activiti 测试
 * @author: 黄天亮
 * @create: 2021-01-20 14:49
 **/
@Slf4j
@SpringBootTest
public class ActivitiTests {
    /**
     * 使用acticiti 提供的默认方式mysql表 （25张）
     */
    @Test
    public void createActiviti() {
        //    使用activiti提供的工具类  ProcessEngines，使用默认方法getDefaultProcessEngine()
        //    getDefaultProcessEngine会默认从resource文件夹下读取名字为activiti.cfg.xml 的文件
        //    创建ProcessEngine 时，就会创建mysql表
        //    默认方式
        //     ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //     RepositoryService repositoryService = processEngine.getRepositoryService();
        //     repositoryService.createDeployment();

        //
        ProcessEngineConfiguration processEngineConfiguration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml",
                                "processEngineConfiguration");

        // 获取流程引擎对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);

    }

    /**
     * 测试流程部署
     * `ACT_RE_PROCDEF`
     * `ACT_RE_DEPLOYMENT`
     * `ACT_GE_BYTEARRAY`
     *
     */
    @Test
    public void testDeployment() {
        //    1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //    3、使用service进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/hello.bpmn")
                .addClasspathResource("bpmn/hello.png")
                .deploy();
        //    4、输出部署信息
        log.info("流程部署ID={}", deploy.getId());
        log.info("流程部署名字={}", deploy.getName());
    }

    /**
     * 启动流程实例
     * `act_hi_actinst` 流程实例执行历史
     * `act_hi_identitylink` 流程参与者的历史信息
     * `act_hi_procinst` 流程实例的历史信息
     * `act_hi_taskinst` 任务的历史信息
     * `act_ru_execution` 流程执行的信息
     * `act_ru_identitylink` 流程参与者信息
     * `act_ru_task` 任务信息
     */
    @Test
    public void testStartProcess() {
        //  1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2、获取 RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //  3、根据流程定义的id启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("hello");
        log.info("流程定义ID：{}", instance.getProcessDefinitionId());
        log.info("流程实例ID：{}", instance.getId());
        log.info("当前活动ID：{}", instance.getActivityId());
        /*
        流程定义ID：hello:1:2504
        流程实例ID：5001
        当前活动ID：null
         */
    }

    /**
     * 获取个任务集
     */
    @Test
    public void testFindPersonalTaskList() {
        //    1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取taskService
        TaskService taskService = processEngine.getTaskService();
        //    3、根据流程 key 和 任务负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("hello")
                .taskAssignee("zhangsan")
                .list();
        //    4、输出
        for (Task task : tasks) {
            log.info("流程实例ID：{}", task.getProcessInstanceId());
            log.info("任务ID：{}", task.getId());
            log.info("任务责任人：{}", task.getAssignee());
            log.info("任务名称：{}", task.getName());
        }

        /*
        流程实例ID：1
        任务ID：5
        任务责任人：zhangsan
        任务名称：创建出差申请
         */
    }

    /**
     * 完成个人任务
     * `ACT_HI_TASKINST`
     * `ACT_HI_ACTINST`
     * `ACT_HI_IDENTITYLINK`
     * `ACT_RU_TASK`
     * `ACT_RU_IDENTITYLINK`  update --4
     * `ACT_HI_TASKINST`  update --5
     * `ACT_RU_EXECUTION`  update --ID_=2  REV_=1
     * `ACT_RU_TASK`  delete --ID_=5  REV_=1
     */
    @Test
    public void completeTask() {
        //    1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //    2、获取taskService
        TaskService taskService = processEngine.getTaskService();
        //    3、根据任务id完成任务,完成zhangsan的任务
        // taskService.complete("5");


        // 获取jery - hello对应的任务
        // Task task = taskService.createTaskQuery()
        //         .processDefinitionKey("hello")
        //         .taskAssignee("jery")
        //         .singleResult();
        // 完成jery的任务
        /*
        流程实例ID：1
        任务ID：2502
        任务责任人：jery
        任务名称：经理审批
         */

        // jk - hello对应的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("hello")
                .taskAssignee("jk")
                .singleResult();
        // 完成jk的任务
        /*
            流程实例ID：1
            任务ID：5002
            任务责任人：jk
            任务名称：总经理审批
         */

        // rosi  - hello对应的任务
        // 。。。同上
        /*
            流程实例ID：1
            任务ID：7502
            任务责任人：rosi
            任务名称：财务审批
         */
        log.info("流程实例ID：{}", task.getProcessInstanceId());
        log.info("任务ID：{}", task.getId());
        log.info("任务责任人：{}", task.getAssignee());
        log.info("任务名称：{}", task.getName());
        taskService.complete(task.getId());
    }


    /**
     * 使用zip包进行批量的部署
     */
    @Test
    public void deployProcessZip() {
        //  1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2、获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 读取资源包文件，构造InputStream
        InputStream inputStream = this.getClass()
                .getClassLoader().getResourceAsStream("bpmn/hello.zip");
        // 使用 inputStream 构造 ZipInputStream
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 使用压缩包的流进行部署
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();

        log.info("流程部署ID={}", deploy.getId());
        log.info("流程部署名字={}", deploy.getName());

        /*
            流程部署ID=1
            流程部署名字=null
         */
    }

    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 获取 ProcessDefinitionQuery 对象
        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        // 查询当前所有的流程定义，返回流程定义信息的集合
        // processDefinitionKey(流程定义key)
        // orderByProcessDefinitionAppVersion 进行排序
        // desc 倒叙
        // list 查询出所有的内容
        List<ProcessDefinition> definitionList = definitionQuery.processDefinitionKey("hello")
                .orderByProcessDefinitionAppVersion()
                .desc()
                .list();
        // 输出信息
        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义ID：" + processDefinition.getId());
            System.out.println("流程定义名称：" + processDefinition.getName());
            System.out.println("流程定义Key：" + processDefinition.getKey());
            System.out.println("流程定义版本：" + processDefinition.getVersion());
            System.out.println("流程部署ID：" + processDefinition.getDeploymentId());
            System.out.println("-----------------------------------");
        }

    }

    /**
     * 删除流程部署信息
     * 主要删除的
     * `ACT_GE_BYTEARRAY`
     * `ACT_RE_DEPLOYMENT`
     * `ACT_RE_PROCDEF`
     * <p>
     * `ACT_RU_EVENT_SUBSCR`
     * `ACT_RU_IDENTITYLINK`
     * 当前的流程如果并没有完成，想要删除的话需要使用特殊方式，原理就是级联删除
     */
    @Test
    public void deleteDeployment() {
        //    获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 通过部署id来删除部署流程信息
        String deleteDeploymentId = "35001";
        // repositoryService.deleteDeployment(deleteDeploymentId);
        // 级联删除
        repositoryService.deleteDeployment(deleteDeploymentId, true);

    }

    /**
     * 流程资源下载
     * 方案1：使用activiti提供的api来下载资源文件
     * 方案2：自己写代码从数据库中下载文件，使用jdbc对blob类型，clob类型数据读取出来，保存到文件目录
     * 解决IO操作：commons-io.jar
     */
    @Test
    public void getDeployment() throws IOException {
        //    获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 获取查询对象 ProcessDefinitionQuery查询流程定义信息
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("hello")
                .singleResult();
        // 通过流程定义信息，获取部署ID
        String deploymentId = definition.getDeploymentId();
        // 通过 repositoryService ，传递部署ID参数，读取资源信息（png,bpmn）
        // 从流程定义表中，获取png图片的目录和名字
        String pngName = definition.getDiagramResourceName();
        // 通过 部署id和文件名字来获取图片的资源
        InputStream pngInputStream = repositoryService.getResourceAsStream(deploymentId, pngName);

        String bpmnName = definition.getResourceName();
        InputStream bpmnInputStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);

        // 构造 FileOutputStream 流
        File pngFile = new File("d:/hello.png");
        File bpmnFile = new File("d:/hello.bpmn");
        FileOutputStream pngOutputStream = new FileOutputStream(pngFile);
        FileOutputStream bpmnOutputStream = new FileOutputStream(bpmnFile);

        // 输出流，输出流的转换
        IOUtils.copy(pngInputStream, pngOutputStream);
        IOUtils.copy(bpmnInputStream, bpmnOutputStream);

        // 关闭流
        pngOutputStream.close();
        bpmnOutputStream.close();
        pngInputStream.close();
        bpmnInputStream.close();

    }

    @Test
    public void findHistoryInfo() {
//    获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        HistoryService historyService = processEngine.getHistoryService();

        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();

        List<HistoricActivityInstance> list =
                instanceQuery
                        .processDefinitionId("hello:1:10004")  // 根据 DefinitionId 查询
                        // .processInstanceId("12501") // 根据 InstanceId 查询
                        .orderByHistoricActivityInstanceStartTime() // 排序 ，根据开始时间排序
                        .asc() // 升序
                        .list();
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("<=====================================>");
        }


    }


}

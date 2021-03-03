package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.common.translate.ObjectMapper;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.entity.view.FlowContractView;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.SysFlowService;
import com.example.carrentalcontract.util.SecurityUtil;
import com.example.carrentalcontract.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/car/contract/activitiHistory")
public class ActivitiHistoryController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private SysFlowService flowService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    ManagementService managementService;

    /**
     * 流程图
     * @param processInstanceId processInstanceId
     * @param f 是否高亮
     * @throws IOException
     */
    @GetMapping("/queryProPlan")
    public void getProcessDiagram(HttpServletRequest request, HttpServletResponse response, String processInstanceId,Boolean f) throws IOException {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        if (processInstance != null) {
            // get process model
            BpmnModel model = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getId());
            if (model !=  null && model.getLocationMap().size() > 0) {
                ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
                InputStream inputStream = null;
                // 生成流程图 已启动的task 高亮
                if (f){
                    inputStream = generator.generateDiagram(model,"png",activeActivityIds,
                            runtimeService.getActiveActivityIds(processInstanceId),"宋体","宋体",ClassLoader.getSystemClassLoader(),1L);
                }else {
                    // inputStream = generator.generateDiagram(model, "png", Collections.<String>emptyList());
                    inputStream = generator.generateDiagram(model,"png",Collections.<String>emptyList(),
                            Collections.<String>emptyList(),"宋体","宋体",ClassLoader.getSystemClassLoader(),1L);
                }

                // 生成流程图 都不高亮
                // InputStream inputStream = generator.generateDiagram(model, "png", Collections.<String>emptyList());
                String imageName = "image" + Instant.now().getEpochSecond()+".png";
                FileUtils.copyInputStreamToFile(inputStream, new File("src/main/resources/resources/bpmn/"+imageName));
                //使用字节流读取本地图片
                ServletOutputStream out=null;
                BufferedInputStream buf=null;
                //创建一个文件对象，对应的文件就是python把词云图片生成后的路径以及对应的文件名
                File file = new File("src/main/resources/resources/bpmn/"+imageName);
                try {
                    //使用输入读取缓冲流读取一个文件输入流
                    buf=new BufferedInputStream(new FileInputStream(file));
                    // buf=new BufferedInputStream(inputStream);
                    //利用response获取一个字节流输出对象
                    out=response.getOutputStream();
                    //定义个数组，由于读取缓冲流中的内容
                    byte[] buffer=new byte[1024];
                    //while循环一直读取缓冲流中的内容到输出的对象中
                    while(buf.read(buffer)!=-1) {
                        out.write(buffer);
                    }
                    //写出到请求的地方
                    out.flush();
                }catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }finally {
                    if(buf!=null) buf.close();
                    if(out!=null) out.close();
                }
                //传输结束后，删除文件，可以不删除，在生成的图片中回对此进行覆盖
                File file1 = new File("src/main/resources/resources/bpmn/"+imageName);
                file1.delete();
                System.out.println("文件删除！");
            }
        }
    }


    /**
     * 查询历史流程实例
     */
    @PostMapping(value = "/getProcinstsByUserName")
    public Result procinstsByUser() {
        String userName = SessionUtil.getCurrentUserName();
        try {
            List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                    .involvedUser(userName)
                    .orderByProcessInstanceStartTime().asc()
                    .list();
            // 查询合同

            List<Long> ids = historicProcessInstances.stream().map(n -> Long.parseLong(n.getBusinessKey().split(":")[1]) )
                    .collect(Collectors.toList());
            List<Contract> contracts = contractService.selectIn(Contract.class, ids).getData();
            List<FlowContractView> flowContractViewList = ObjectMapper.clone(contracts, FlowContractView.class);
            ObjectMapper.clone(historicProcessInstances,flowContractViewList);
            for (HistoricProcessInstance instance : historicProcessInstances) {
                // 获取 TaskInfo
                for (FlowContractView view : flowContractViewList) {
                    if (instance.getBusinessKey().indexOf(view.getId().toString())>0){
                        TaskInfo info = new TaskInfo();
                        ObjectMapper.clone(instance,info);
                        view.setTaskInfo(info);
                        break;
                    }
                }
            }
            return Result.success(flowContractViewList);
        } catch (Exception e) {
            return new Result(901,"获取历史任务失败");
        }

    }


    /**
     * 查询已处理任务列表。
     *
     * @return 已处理任务列表
     */
    @PostMapping(value = "/queryDoneTasks")
    public Result<PageInfo<FlowContractView>> queryDoneTasks(@RequestBody FlowContractView contract) {
        PageInfo<FlowContractView> contractViewPageInfo = new PageInfo<>();
        try {
            String username = SessionUtil.getCurrentUserName();
            List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(username)
                    .finished()
                    .list();
            // 过滤掉创建合同这一步骤
            List<String> flowIds = list.stream()
                    .filter(item -> !item.getTaskDefinitionKey().equals("_2"))
                    .map(HistoricTaskInstance::getExecutionId)
                    .distinct()
                    .collect(Collectors.toList());
            log.info("查询已处理的任务集 ExecutionIds：{}",flowIds);
            if (flowIds.isEmpty()){
                return Result.success();
            }
            List<SysFlow> flows = flowService.selectInPropertyIds(flowIds,"executionId").getData();
            List<Long> ids = flows.stream().map(n -> Long.parseLong(n.getBusinessKey().split(":")[1]))
                    .collect(Collectors.toList());

            PageInfo<FlowContractView> data = contractService.selectInIds(contract, ids).getData();
            // List<Contract> contracts = contractService.selectIn(Contract.class, ids).getData();
            // List<FlowContractView> flowContractViewList = ObjectMapper.clone(data.getList(), FlowContractView.class);
            for (SysFlow flow : flows) {
                // 获取 TaskInfo
                for (FlowContractView view : data.getList()) {
                    if (flow.getBusinessKey().indexOf(view.getId().toString())>0){
                        TaskInfo info = new TaskInfo();
                        ObjectMapper.clone(flow,info);
                        view.setTaskInfo(info);
                        break;
                    }
                }
            }

            // ObjectMapper.clone(data, contractViewPageInfo);
            // contractViewPageInfo.setList(flowContractViewList);
            return Result.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return new Result(901,"获取历史任务失败");
        }

    }

    /**
     * 我的发起任务
     * @param contract
     * @return
     */
    @PostMapping(value = "/queryMyStartTasks")
    public Result<PageInfo<FlowContractView>> queryMyStartTasks(@RequestBody FlowContractView contract) {
        PageInfo<FlowContractView> contractViewPageInfo = new PageInfo<>();
        // try {
            String username = SessionUtil.getCurrentUserName();
            List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                    .startedBy(username)
                    .list();
            List<Long> ids = list.stream().map(n -> Long.parseLong(n.getBusinessKey().split(":")[1]) )
                    .collect(Collectors.toList());
            if (ids.isEmpty()){
                ObjectMapper.clone(contract, contractViewPageInfo);
                return Result.success(contractViewPageInfo);
            }
            PageInfo<FlowContractView> data = contractService.selectInIds(contract, ids).getData();
            // List<Contract> contracts = contractService.selectIn(Contract.class, ids).getData();
            // List<FlowContractView> flowContractViewList = ObjectMapper.clone(data.getList(), FlowContractView.class);
            for (HistoricProcessInstance instance : list) {
                // 获取 TaskInfo6
                for (FlowContractView view : data.getList()) {
                    if (instance.getBusinessKey().indexOf(view.getId().toString())>0){
                        TaskInfo info = new TaskInfo();
                        ObjectMapper.clone(instance,info);
                        view.setTaskInfo(info);
                        break;
                    }
                }
            }
            // ObjectMapper.clone(data, contractViewPageInfo);
            // contractViewPageInfo.setList(flowContractViewList);
            return Result.success(data);
        //
        // } catch (Exception e) {
        //     return new Result(901,"获取历史任务失败");
        // }

    }

    //任务实例历史
    @PostMapping(value = "/getInstancesByPiID")
    public Result getInstancesByPiID(@RequestParam("piID") String piID) {
        try {

            //--------------------------------------------另一种写法-------------------------
            List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                    .orderByHistoricTaskInstanceEndTime().asc()
                    .processInstanceId(piID)
                    .list();
            return Result.success(historicTaskInstances);
        } catch (Exception e) {
            return new Result(901,"获取历史任务失败");
        }

    }


}

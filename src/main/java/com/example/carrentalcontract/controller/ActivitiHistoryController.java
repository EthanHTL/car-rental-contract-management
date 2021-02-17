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
     *
     * @param processInstanceId
     * @param f
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
    public Result queryDoneTasks() {
        try {
            String username = SessionUtil.getCurrentUserName();
            List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(username)
                    .finished()
                    .list();
            List<String> flowIds = list.stream()
                    .filter(item -> !item.getTaskDefinitionKey().equals("_2"))
                    .map(HistoricTaskInstance::getExecutionId)
                    .distinct()
                    .collect(Collectors.toList());
            log.info("查询已处理的任务集 ExecutionIds：{}",flowIds);
            List<SysFlow> flows = flowService.selectInPropertyIds(flowIds,"executionId").getData();
            List<Long> ids = flows.stream().map(n -> Long.parseLong(n.getBusinessKey().split(":")[1]))
                    .collect(Collectors.toList());
            List<Contract> contracts = contractService.selectIn(Contract.class, ids).getData();
            List<FlowContractView> flowContractViewList = ObjectMapper.clone(contracts, FlowContractView.class);
            for (SysFlow flow : flows) {
                // 获取 TaskInfo
                for (FlowContractView view : flowContractViewList) {
                    if (flow.getBusinessKey().indexOf(view.getId().toString())>0){
                        TaskInfo info = new TaskInfo();
                        ObjectMapper.clone(flow,info);
                        view.setTaskInfo(info);
                        break;
                    }
                }
            }

            return Result.success(flowContractViewList);
        } catch (Exception e) {
            System.out.println(e);
            return new Result(901,"获取历史任务失败");
        }

    }

    @PostMapping(value = "/queryMyStartTasks")
    public Result queryMyStartTasks() {
        try {
            String username = SessionUtil.getCurrentUserName();
            List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                    .startedBy(username)
                    .list();
            List<Long> ids = list.stream().map(n -> Long.parseLong(n.getBusinessKey().split(":")[1]) )
                    .collect(Collectors.toList());
            List<Contract> contracts = contractService.selectIn(Contract.class, ids).getData();
            List<FlowContractView> flowContractViewList = ObjectMapper.clone(contracts, FlowContractView.class);
            for (HistoricProcessInstance instance : list) {
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

    //流程图高亮
    @PostMapping("/gethighLine")
    public Result gethighLine(@RequestParam("instanceId") String instanceId) {
        try {
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(instanceId).singleResult();
            //获取bpmnModel对象
            BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
            //因为我们这里只定义了一个Process 所以获取集合中的第一个即可
            Process process = bpmnModel.getProcesses().get(0);
            //获取所有的FlowElement信息
            Collection<FlowElement> flowElements = process.getFlowElements();

            Map<String, String> map = new HashMap<>();
            for (FlowElement flowElement : flowElements) {
                //判断是否是连线
                if (flowElement instanceof SequenceFlow) {
                    SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
                    String ref = sequenceFlow.getSourceRef();
                    String targetRef = sequenceFlow.getTargetRef();
                    map.put(ref + targetRef, sequenceFlow.getId());
                }
            }

            //获取流程实例 历史节点(全部)
            List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId)
                    .list();
            //各个历史节点   两两组合 key
            Set<String> keyList = new HashSet<>();
            for (HistoricActivityInstance i : list) {
                for (HistoricActivityInstance j : list) {
                    if (i != j) {
                        keyList.add(i.getActivityId() + j.getActivityId());
                    }
                }
            }
            //高亮连线ID
            Set<String> highLine = new HashSet<>();
            keyList.forEach(s -> highLine.add(map.get(s)));


            //获取流程实例 历史节点（已完成）
            List<HistoricActivityInstance> listFinished = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId)
                    .finished()
                    .list();
            //高亮节点ID
            Set<String> highPoint = new HashSet<>();
            listFinished.forEach(s -> highPoint.add(s.getActivityId()));

            //获取流程实例 历史节点（待办节点）
            List<HistoricActivityInstance> listUnFinished = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId)
                    .unfinished()
                    .list();

            //需要移除的高亮连线
            Set<String> set = new HashSet<>();
            //待办高亮节点
            Set<String> waitingToDo = new HashSet<>();
            listUnFinished.forEach(s -> {
                waitingToDo.add(s.getActivityId());

                for (FlowElement flowElement : flowElements) {
                    //判断是否是 用户节点
                    if (flowElement instanceof UserTask) {
                        UserTask userTask = (UserTask) flowElement;

                        if (userTask.getId().equals(s.getActivityId())) {
                            List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
                            //因为 高亮连线查询的是所有节点  两两组合 把待办 之后  往外发出的连线 也包含进去了  所以要把高亮待办节点 之后 即出的连线去掉
                            if (outgoingFlows != null && outgoingFlows.size() > 0) {
                                outgoingFlows.forEach(a -> {
                                    if (a.getSourceRef().equals(s.getActivityId())) {
                                        set.add(a.getId());
                                    }
                                });
                            }
                        }
                    }
                }
            });

            highLine.removeAll(set);


            //获取当前用户
            //User sysUser = getSysUser();
            Set<String> iDo = new HashSet<>(); //存放 高亮 我的办理节点
            //当前用户已完成的任务

            String AssigneeName = null;
            // if (GlobalConfig.Test) {
            //     AssigneeName = "bajie";
            // } else {
            //     AssigneeName = UuserInfoBean.getUsername();
            // }

            List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(AssigneeName)
                    .finished()
                    .processInstanceId(instanceId).list();

            taskInstanceList.forEach(a -> iDo.add(a.getTaskDefinitionKey()));

            Map<String, Object> reMap = new HashMap<>();
            reMap.put("highPoint", highPoint);
            reMap.put("highLine", highLine);
            reMap.put("waitingToDo", waitingToDo);
            reMap.put("iDo", iDo);

            return Result.success(reMap);

        } catch (Exception e) {
            return new Result(901,"渲染历史流程失败");
        }
    }



}

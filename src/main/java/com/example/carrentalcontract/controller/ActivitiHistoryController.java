package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.common.translate.ObjectMapper;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.entity.view.FlowContractView;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.util.SecurityUtil;
import com.example.carrentalcontract.util.SessionUtil;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/car/contract/activitiHistory")
public class ActivitiHistoryController {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private ContractService contractService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessEngine processEngine;

    //用户历史
    @PostMapping(value = "/getInstancesByUserName")
    public Result InstancesByUser() {
        String userName = SessionUtil.getCurrentUserName();
        try {

            List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                    .orderByHistoricTaskInstanceEndTime().asc()
                    .taskAssignee(userName)
                    .finished()
                    .list();
            for(HistoricTaskInstance hti:historicTaskInstances){
                System.out.println("任务ID:"+hti.getId());
                System.out.println("流程实例ID:"+hti.getProcessInstanceId());
                System.out.println("任务名称："+hti.getName());
                System.out.println("办理人："+hti.getAssignee());
                System.out.println("开始时间："+hti.getStartTime());
                System.out.println("结束时间："+hti.getEndTime());
                System.out.println("=================================");
            }

            return Result.success(historicTaskInstances);
        } catch (Exception e) {
            return new Result(901,"获取历史任务失败");
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
            List<FlowContractView> flowContractViewList = new ArrayList<>();
            for (HistoricProcessInstance instance : historicProcessInstances) {
                String id = instance.getBusinessKey().split(":")[1];
                Contract contract = contractService.selectByPrimaryKey(Long.parseLong(id)).getData();
                TaskInfo taskInfo = new TaskInfo();
                ObjectMapper.clone(instance,taskInfo);
                FlowContractView contractView = new FlowContractView();
                ObjectMapper.clone(contract,contractView);
                contractView.setTaskInfo(taskInfo);
                flowContractViewList.add(contractView);
            }


            return Result.success(flowContractViewList);
        } catch (Exception e) {
            return new Result(901,"获取历史任务失败");
        }

    }


    //用户历史
    @PostMapping(value = "/getMyHistory")
    public Result getMyHistory() {
        String username = SessionUtil.getCurrentUserName();
        try {
            List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                    .startedBy(username)
                    .list();
            return Result.success(list);
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

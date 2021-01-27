package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.sercive.ProcessInstanceService;
import com.example.carrentalcontract.util.SecurityUtil;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-27 10:10
 **/
@Service("processInstanceService")
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RepositoryService repositoryService;


    @Override
    public Result getInstances() {
        Page<ProcessInstance> processInstances = null;
        try {
            processInstances = processRuntime.processInstances(Pageable.of(0, 50));
            //System.out.println("流程实例数量： " + processInstances.getTotalItems());
            List<ProcessInstance> list = processInstances.getContent();
            //list.sort((y,x)->x.getProcessDefinitionVersion()-y.getProcessDefinitionVersion());
            list.sort((y, x) -> x.getStartDate().toString().compareTo(y.getStartDate().toString()));

            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
            for (ProcessInstance pi : list) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", pi.getId());
                hashMap.put("name", pi.getName());
                hashMap.put("status", pi.getStatus());
                hashMap.put("processDefinitionId", pi.getProcessDefinitionId());
                hashMap.put("processDefinitionKey", pi.getProcessDefinitionKey());
                hashMap.put("startDate", pi.getStartDate());
                hashMap.put("processDefinitionVersion", pi.getProcessDefinitionVersion());
                //因为processRuntime.processDefinition("流程部署ID")查询的结果没有部署流程与部署ID，所以用repositoryService查询
                ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                        .processDefinitionId(pi.getProcessDefinitionId())
                        .singleResult();
                hashMap.put("resourceName", pd.getResourceName());
                hashMap.put("deploymentId", pd.getDeploymentId());
                listMap.add(hashMap);
            }

            return Result.success(listMap);
        } catch (Exception e) {
            return new Result(901, "获取流程实例失败");

        }
    }


    //启动
    @Override
    public Result startProcess(String processDefinitionKey,
                               String instanceName,
                               String instanceVariable,
                               String businessKey) {
        try {
            ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                    .start()
                    .withProcessDefinitionKey(processDefinitionKey)
                    .withName(instanceName)
                    //.withVariable("content", instanceVariable)
                    //.withVariable("参数2", "参数2的值")
                    .withBusinessKey(businessKey)
                    .build());
            return Result.success(processInstance.getName() + "；" + processInstance.getId());
        } catch (Exception e) {
            return new Result(901, "创建流程实例失败");
        }
    }

    //删除
    @Override
    public Result deleteInstance(String instanceID) {
        try {

            ProcessInstance processInstance = processRuntime.delete(ProcessPayloadBuilder
                    .delete()
                    .withProcessInstanceId(instanceID)
                    .build()
            );
            return Result.success(processInstance.getName());
        } catch (Exception e) {
            return new Result(901, "删除流程实例失败");

        }

    }

    //挂起冷冻
    @Override
    public Result suspendInstance(String instanceID) {

        try {

            ProcessInstance processInstance = processRuntime.suspend(ProcessPayloadBuilder
                    .suspend()
                    .withProcessInstanceId(instanceID)
                    .build()
            );
            return Result.success(processInstance.getName());

        } catch (Exception e) {
            return new Result(901, "挂起流程实例失败");

        }
    }

    //激活
    @Override
    public Result resumeInstance(String instanceID) {

        try {

            ProcessInstance processInstance = processRuntime.resume(ProcessPayloadBuilder
                    .resume()
                    .withProcessInstanceId(instanceID)
                    .build()
            );
            return Result.success(processInstance.getName());
        } catch (Exception e) {
            return new Result(901, "激活流程实例失败");

        }
    }


    //获取参数
    @Override
    public Result variables(String instanceID) {
        try {
            List<VariableInstance> variableInstance = processRuntime.variables(ProcessPayloadBuilder
                    .variables()
                    .withProcessInstanceId(instanceID)
                    .build());

            return Result.success(variableInstance);
        } catch (Exception e) {
            return new Result(901, "获取流程参数失败");
        }
    }


}

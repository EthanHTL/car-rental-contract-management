package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.common.translate.ObjectMapper;
import com.example.carrentalcontract.entity.en.CheckEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.entity.view.FlowContractView;
import com.example.carrentalcontract.mapper.ContractMapper;
import com.example.carrentalcontract.sercive.*;
import com.example.carrentalcontract.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 合同表(Contract)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:55
 */
@Slf4j
@Service("contractService")
public class ContractServiceImpl extends DbServiceImpl<Contract> implements ContractService {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ProcessDefinitionService definitionService;
    @Autowired
    private SysDictDetailService sysDictDetailService;
    @Autowired
    private ProcessInstanceService instanceService;
    @Autowired
    private SysFlowService flowService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ActFlowCommService actFlowCommService;
    @Resource
    private ContractMapper contractMapper;


    @Override
    public Result<List<Contract>> findAll() {
        return super.selectAll();
    }

    // @PreAuthorize("hasAnyRole('common')")
    @Override
    public Result<PageInfo<FlowContractView>> findPage(Contract contract) {
        Weekend<Contract> weekend = new Weekend<>(Contract.class);
        Example.Criteria criteria = weekend.createCriteria();
        if (StringUtils.isNotBlank(contract.getContractName())) {
            criteria.andLike("contractName", "%" + contract.getContractName() + "%");
        }
        if (contract.getState()!=null){
            criteria.andEqualTo("state",contract.getState());
        }

        PageInfo<Contract> pageInfoResult = super.selectPage(weekend, contract.getPageNum(), contract.getPageSize()).getData();
        List<Long> ids = pageInfoResult.getList().stream().map(Contract::getId).collect(Collectors.toList());
        Weekend<SysFlow> weekendf = new Weekend<>(SysFlow.class);
        Example.Criteria criteria1 = weekendf.createCriteria();
        if (ids.size()>0){
            criteria1.andIn("contractId",ids);
        }
        List<SysFlow> data = flowService.select(weekendf).getData();
        PageInfo<FlowContractView> pageInfo = new PageInfo<>();
        ObjectMapper.clone(pageInfoResult,pageInfo);
        if (pageInfo.getList() == null){
            return Result.success(pageInfo);
        }
        pageInfo.setList(new ArrayList<>());
        pageInfoResult.getList().forEach(item ->{
            FlowContractView view = new FlowContractView();
            ObjectMapper.clone(item,view);
            List<SysFlow> sysFlows = data.stream().filter(flow -> flow.getContractId().equals(item.getId()))
                    .collect(Collectors.toList());
            if (sysFlows.size() > 0) {
                view.setFlow(sysFlows.get(0));
            }
            pageInfo.getList().add(view);
        });

        return Result.success(pageInfo);
    }

    @Override
    public Result<Contract> insert(@NonNull Contract contract) {
        return super.insert(contract);
    }

    @Transactional
    @Override
    public Result createContract(Contract contract, SysUser user) {
        contract.setPrincipal(user.getId()); // 合同负责人
        contract.setSignTime(new Date());
        contract.setState(1);
        contract.setCreatorId(user.getId());
        // 创建合同
        Result<Contract> data = super.insert(contract);
        if (data.getStatusCode() == 200) {
            // 启动流程
            Contract c = data.getData();
            Long id = c.getId();
            String formKey = "contract";
            String beanName = formKey + "FlowService";
            // 使用流程变量设置字符串（格式：Contract：id 的形式）
            // 使用正在执行对象表中的一个字典BUSINESS_KEY(Activiti 提供的一个字典)，让启动的流程（流程实例）关联业务
            String businessKey = formKey + ":" + id;

            // 设置流程变量
            Map<String, Object> variables = setVariables(id);
            ProcessInstance processInstance = actFlowCommService.startProcess(formKey, beanName,
                    businessKey, c.getId(),variables,user.getUsername());
            String processDefinitionId = processInstance.getProcessDefinitionId();
            String processBusinessKey = processInstance.getBusinessKey();
            log.info("processDefinitionId is {}" , processDefinitionId);
            SysFlow flow = new SysFlow();
            flow.setFlowName(contract.getContractUsername()+"汽车租赁合同审核");
            flow.setUserId(user.getId());
            flow.setUsername(user.getUsername());
            flow.setContractId(id);
            flow.setState(CheckEnum.PENDING.getStatus());
            flow.setBusinessKey(businessKey);
            String executionId = ((ExecutionEntityImpl) processInstance).getExecutions().get(0).getTasks().get(0).getExecutionId();
            flow.setExecutionId(executionId);
            flow.setProcessInstanceId(processInstance.getProcessInstanceId());
            flow.setProcessDefinitionId(processDefinitionId);
            log.info("创建合同：ExecutionId：{}" , processInstance.getId());
            flowService.insert(flow);
            List<TaskInfo> taskInfos = actFlowCommService.myTaskList(user.getUsername());
            if (!CollectionUtils.isEmpty(taskInfos)){
                for (TaskInfo info : taskInfos) {
                    if (info.getAssignee().equals(user.getUsername())
                            && info.getProcessDefinitionId().equals(processDefinitionId)
                            && info.getBusinessKey().equals(processBusinessKey)
                    ){
                        log.info("complete task , processDefinitionId is {}", info.getProcessDefinitionId());
                        log.info("complete task , taskId is {}", info.getTaskId());
                        actFlowCommService.completeProcess("同意",info.getTaskId(), user.getUsername());
                    }
                }
            }
        }
        // 启动合同审核流程
        return data;

    }

    @Override
    public Result renewContract(Contract contract, SysUser user) {
        contract.setPrincipal(user.getId()); // 合同负责人
        Result<Contract> data = super.insert(contract);

        if (data.getStatusCode() == 200) {
            // 启动流程
            Contract c = data.getData();
            Long id = c.getId();
            String formKey = "contract";
            String beanName = formKey + "FlowService";
            // 使用流程变量设置字符串（格式：Contract：id 的形式）
            // 使用正在执行对象表中的一个字典BUSINESS_KEY(Activiti 提供的一个字典)，让启动的流程（流程实例）关联业务
            String businessKey = formKey + ":" + id;

            // 设置流程变量
            Map<String, Object> variables = setVariables(id);
            ProcessInstance processInstance = actFlowCommService.startProcess(formKey, beanName,
                    businessKey, c.getId(),variables,user.getUsername());
            String processDefinitionId = processInstance.getProcessDefinitionId();
            String processBusinessKey = processInstance.getBusinessKey();
            log.info("processDefinitionId is {}" , processDefinitionId);
            // 开启合同审核流程
            c.setState(CheckEnum.PENDING.getStatus());
            super.update(c);
            SysFlow flow = new SysFlow();
            flow.setFlowName(contract.getContractUsername()+"续期合同审核");
            flow.setUserId(user.getId());
            flow.setUsername(user.getUsername());
            flow.setContractId(id);
            flow.setState(CheckEnum.PENDING.getStatus());
            flow.setBusinessKey(businessKey);
            String executionId = ((ExecutionEntityImpl) processInstance).getExecutions().get(0).getTasks().get(0).getExecutionId();
            flow.setExecutionId(executionId);
            flow.setProcessInstanceId(processInstance.getProcessInstanceId());
            flow.setProcessDefinitionId(processDefinitionId);
            log.info("创建合同：ExecutionId：{}" , processInstance.getId());
            flowService.insert(flow);
            List<TaskInfo> taskInfos = actFlowCommService.myTaskList(user.getUsername());
            if (!CollectionUtils.isEmpty(taskInfos)){
                for (TaskInfo info : taskInfos) {
                    if (info.getAssignee().equals(user.getUsername())
                            && info.getProcessDefinitionId().equals(processDefinitionId)
                            && info.getBusinessKey().equals(processBusinessKey)
                    ){
                        log.info("complete task , processDefinitionId is {}", info.getProcessDefinitionId());
                        log.info("complete task , taskId is {}", info.getTaskId());
                        actFlowCommService.completeProcess("同意",info.getTaskId(), user.getUsername());
                    }
                }
            }
        }
        // 启动合同审核流程
        return data;
    }

    @Override
    public Result<List<Contract>> selectPassAll() {
        return Result.success(contractMapper.selectPassAll());
    }

    @Override
    public Map<String, Object> setVariables(Long id) {
        // 设置流程变量
        Map<String, Object> variables = new HashMap<>();
        // 创建合同和业务员审核 都是业务员操作
        variables.put("assignee0", SessionUtil.getCurrentUser().getUsername());// 创建合同 执行者，当前登录的业务员
        // variables.put("assignee1", SessionUtil.getCurrentUser().getUsername()); // 业务1
        // 经理和总经理
        // variables.put("assignee2", 1101121505827021L); // 经理1
        // variables.put("assignee3", 1101021813661465L); // 总经理1
        return variables;
    }

    @Override
    public Result<List<FlowContractView>> findMyTask(List<TaskInfo> infos) {
        List<FlowContractView> myTaskList = new ArrayList<>();
        infos.forEach(item ->{
            String id = item.getBusinessKey().split(":")[1];
            Contract contract = super.selectByPrimaryKey(Long.parseLong(id)).getData();
            FlowContractView flowContractView = new FlowContractView();
            ObjectMapper.clone(contract,flowContractView);
            flowContractView.setTaskInfo(item);
            myTaskList.add(flowContractView);
        });

        return Result.success(myTaskList);
    }

    @Override
    public Result<PageInfo<FlowContractView>> selectInIds(FlowContractView contract, List<Long> ids) {

        // Weekend<Contract> weekend = new Weekend<>(Contract.class);
        // Example.Criteria criteria = weekend.createCriteria();
        // if (StringUtils.isNotBlank(contract.getContractName())) {
        //     criteria.andLike("contractName", "%" + contract.getContractName() + "%");
        // }
        // if (contract.getState()!=null){
        //     criteria.andEqualTo("state",contract.getState());
        // }
        // criteria.andIn("id",ids);
        Integer pageNum = (contract.getPageNum()-1)* contract.getPageSize();
        Integer pageSize = contract.getPageSize();
        List<FlowContractView> contractList = contractMapper.findPage(contract,ids,pageNum,pageSize);
        PageInfo info = new PageInfo(contractList);
        return Result.success(info);
    }


}
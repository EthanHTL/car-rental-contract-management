package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysDictDetail;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.mapper.ContractMapper;
import com.example.carrentalcontract.sercive.*;
import com.example.carrentalcontract.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 合同表(Contract)表服务实现类
 *
 * @author makejava
 * @since 2020-12-27 22:11:55
 */
@Slf4j
@Service("contractService")
public class ContractServiceImpl extends DbServiceImpl<Contract> implements ContractService {

    @Resource
    private ContractMapper contractMapper;
    @Autowired
    private ProcessDefinitionService definitionService;
    @Autowired
    private SysDictDetailService sysDictDetailService;
    @Autowired
    private ProcessInstanceService instanceService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ActFlowCommService actFlowCommService;


    @Override
    public Result<List<Contract>> findAll() {
        return super.selectAll();
    }

    @PreAuthorize("hasAnyRole('common')")
    @Override
    public Result<PageInfo<Contract>> findPage(Contract contract) {
        PageInfo info = new PageInfo();
        Weekend<Contract> weekend = new Weekend<>(Contract.class);
        Example.Criteria criteria = weekend.createCriteria();
        if (StringUtils.isNotBlank(contract.getContractName())) {
            criteria.andLike("contractName", "%" + contract.getContractName() + "%");
        }

        return super.selectPage(weekend, contract.getPageNum(), contract.getPageSize());
    }

    @Override
    public Result<Contract> insert(@NonNull Contract contract) {
        return super.insert(contract);
    }

    @Transactional
    @Override
    public Result createContract(Contract contract, Long userId) {
        SysDictDetail contractActiviti = sysDictDetailService.getDictDataByTypeAndId("file", "10").getData();

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
            ProcessInstance processInstance = actFlowCommService.startProcess(formKey, beanName, businessKey, c.getId());
            // 流程实例id
            String processDefinitionId = processInstance.getProcessDefinitionId();
            log.info("processDefinitionId is {}", processDefinitionId);
            // processDefinitionId is contract:1:5003
            List<Map<String, Object>> taskList = actFlowCommService.myTaskList(contract.getContactUserId().toString());
            if (!CollectionUtils.isEmpty(taskList)){
                for (Map<String, Object> map : taskList) {
                    if (map.get("assignee").toString().equals(userId.toString())
                    && map.get("processDefinitionId").toString().equals(processDefinitionId)){
                        log.info("processDefinitionId is {}", map.get("processDefinitionId"));
                        log.info("taskId is {}", map.get("taskId").toString());
                        Result result = actFlowCommService.completeProcess("同意", map.get("taskId").toString(), userId.toString());
                    }
                }
            }
        }
        // 启动合同审核流程
        return Result.success();

    }


}
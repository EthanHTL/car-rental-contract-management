package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.sercive.ActFlowCommService;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.example.carrentalcontract.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合同表(Contract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/contract")
@Slf4j
public class ContractController {
    /**
     * 服务对象
     */
    @Resource
    private ContractService contractService;
    @Resource
    private SysResourceService sysResourceService;
    @Autowired
    private ActFlowCommService actFlowCommService;

    /**
     * 分页
     */
    @PostMapping("/find/page")
    public Result<PageInfo<Contract>> findPage(@RequestBody Contract contract) {
        return this.contractService.findPage(contract);
    }

    /**
     * 查询所有合同
     *
     */
    @PostMapping("/find/all")
    public Result<List<Contract>> findAll() {
        return this.contractService.findAll();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("files") MultipartFile[] files) {
        return sysResourceService.uploadFiles(files);
    }

    @PostMapping("/create")
    public Result createContract(@RequestBody Contract contract){
        SysUser currentUser = SessionUtil.getCurrentUser();
        return contractService.createContract(contract,currentUser);
    }
    @PostMapping("/flow/tasks")
    public Result findMyTaskList(){
        String uid = SessionUtil.getCurrentUser().getUsername();
        // 1101110410767877
        List<Map<String, Object>> maps = actFlowCommService.myTaskList(uid);
        // {
        //   "processInstanceId": "ce895fe2-6476-11eb-8357-00e04c031e96",
        //   "processDefinitionId": "contract:1:5003",
        //   "assigneeUser": "user1",
        //   "priority": 50,
        //   "executionId": "ce8a4a4d-6476-11eb-8357-00e04c031e96",
        //   "createTime": 1612174699932,
        //   "taskName": "创建合同",
        //   "assignee": "1101121603322469",
        //   "taskId": "ce921280-6476-11eb-8357-00e04c031e96"
        // }
        return Result.success(maps);
    }

    @PostMapping("/flow/tasks/group")
    public Result findMyGTaskList(){
        String username = SessionUtil.getCurrentUserName();

        List<Map<String, Object>> maps = actFlowCommService.myGTaskList(username);

        return Result.success(maps);
    }

    @PostMapping("/flow/task/complete")
    public Result complete(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();

        Map<String, Object> variables = new HashMap<>();
        variables.put("contract",taskInfo);
        actFlowCommService.setLocalVariables(taskInfo.getTaskId(),variables);
        return actFlowCommService.completeProcess(taskInfo.getRemark(),taskInfo.getTaskId(),userName);
    }

    @PostMapping("/flow/task/claim")
    public Result claimTask(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();
;
        return actFlowCommService.claimTask(taskInfo.getTaskId(),userName);
    }
    @PostMapping("/flow/task/return")
    public Result assigneeToGroupTask(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();
        return actFlowCommService.assigneeToGroupTask(taskInfo.getTaskId(),userName);
    }
    @PostMapping("/flow/deployment/delete")
    public Result deleteDeployment(String deploymentId){
        String userName = SessionUtil.getCurrentUserName();
        return actFlowCommService.deleteDeployment(deploymentId,true);
    }
    @PostMapping("/flow/deployment")
    public Result deployment(String name, String path, String imgPath){
        return actFlowCommService.deployment(name,path,imgPath);
    }






}
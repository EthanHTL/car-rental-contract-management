package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.request.TaskInfo;
import com.example.carrentalcontract.entity.view.FlowContractView;
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

    // 创建合同
    @PostMapping("/create")
    public Result createContract(@RequestBody Contract contract){
        SysUser currentUser = SessionUtil.getCurrentUser();
        return contractService.createContract(contract,currentUser);
    }

    // 创建合同
    @PostMapping("/renew")
    public Result renewContract(@RequestBody Contract contract){
        SysUser currentUser = SessionUtil.getCurrentUser();
        return contractService.renewContract(contract,currentUser);
    }

    // 我的任务（个人+组 任务）
    @PostMapping("/flow/tasks")
    public Result<List<FlowContractView>> findMyTaskList(){
        String username = SessionUtil.getCurrentUserName();
        if (username == null){
            return new Result(901,"未登陆");
        }
        List<TaskInfo> infos = actFlowCommService.myTaskList(username);
        List<TaskInfo> Ginfos = actFlowCommService.myGTaskList(username);
        Ginfos.forEach(item ->{
            // 拾取任务
            actFlowCommService.claimTask(item.getTaskId(),username);
        });
        infos.addAll(Ginfos);
        return contractService.findMyTask(infos);
    }

    @PostMapping("/flow/tasks/group")
    public Result findMyGTaskList(){
        String username = SessionUtil.getCurrentUserName();
        List<TaskInfo> infos = actFlowCommService.myGTaskList(username);

        return Result.success(infos);
    }

    // 完成任务
    @PostMapping("/flow/task/complete")
    public Result complete(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("contract",taskInfo);
        actFlowCommService.setLocalVariables(taskInfo.getTaskId(),variables);
        return actFlowCommService.completeProcess(taskInfo.getRemark(),taskInfo.getTaskId(),userName);
    }

    // 任务拾取
    @PostMapping("/flow/task/claim")
    public Result claimTask(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();
;
        return actFlowCommService.claimTask(taskInfo.getTaskId(),userName);
    }
    // 任务归还
    @PostMapping("/flow/task/return")
    public Result assigneeToGroupTask(@RequestBody TaskInfo taskInfo){
        String userName = SessionUtil.getCurrentUserName();
        return actFlowCommService.assigneeToGroupTask(taskInfo.getTaskId(),userName);
    }

    // 删除
    @PostMapping("/flow/deployment/delete")
    public Result deleteDeployment(String deploymentId){
        String userName = SessionUtil.getCurrentUserName();
        return actFlowCommService.deleteDeployment(deploymentId,true);
    }
    // 任务部署
    @PostMapping("/flow/deployment")
    public Result deployment(String name, String path, String imgPath){
        return actFlowCommService.deployment(name,path,imgPath);
    }


}
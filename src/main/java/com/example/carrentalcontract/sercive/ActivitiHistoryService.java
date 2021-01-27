package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface ActivitiHistoryService {
    //用户历史
    Result InstancesByUser();

    //任务实例历史
    Result getInstancesByPiID(@RequestParam("piID") String piID);

    //流程图高亮
    Result gethighLine(@RequestParam("instanceId") String instanceId);
}

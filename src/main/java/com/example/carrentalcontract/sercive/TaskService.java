package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface TaskService {
    //获取我的代办任务
    Result getTasks();

    //完成待办任务
    Result completeTask(@RequestParam("taskID") String taskID);
}

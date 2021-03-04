package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysFlow;
import com.example.carrentalcontract.entity.request.TaskInfo;
import lombok.Data;

@Data
public class FlowContractView extends Contract {

    private TaskInfo taskInfo;
    private SysFlow flow;
}

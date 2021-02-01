package com.example.carrentalcontract.listener.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * 流程启动监听器
 */
public class ActivitiProcessStartedListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent activitiEvent) {

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}

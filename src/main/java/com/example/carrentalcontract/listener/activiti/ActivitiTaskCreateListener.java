package com.example.carrentalcontract.listener.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class ActivitiTaskCreateListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent activitiEvent) {

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}

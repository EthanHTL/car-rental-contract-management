package com.example.carrentalcontract.controller;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.sercive.ProcessDefinitionService;
import com.example.carrentalcontract.util.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipInputStream;


@RestController
@RequestMapping("/api/v1/car/processDefinition")
public class ProcessDefinitionController {

    @Autowired
    private ProcessDefinitionService definitionService;

    @PostMapping(value = "/addDeploymentByFileNameBPMN")
    public Result addDeploymentByFileNameBPMN(String deploymentFileUUID, String deploymentName) {
        return definitionService.addDeploymentByFileNameBPMN(deploymentFileUUID,deploymentName);
    }

    @PostMapping(value = "/getDefinitions")
    public Result getDefinitions() {
        return definitionService.getDefinitions();
    }

    @PostMapping(value = "/getDeployments")
    public Result getDeployments() {
        return definitionService.getDeployments();
    }
    @PostMapping(value = "/getInstances")
    public Result getInstances() {
        return definitionService.getDeployments();
    }

}

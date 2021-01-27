package com.example.carrentalcontract.sercive;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Flow;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 流程表(Flow)表服务接口
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public interface ProcessDefinitionService {

    public Result uploadStreamAndDeployment(MultipartFile multipartFile);

    Result upload(HttpServletRequest request, MultipartFile multipartFile);

    Result addDeploymentByFileNameBPMN(String deploymentFileUUID,String deploymentName);

    Result addDeploymentByString(String stringBPMN);

    Result getDefinitions();

    //获取流程定义XML
    void getProcessDefineXML(HttpServletResponse response,
                             String deploymentId,
                             String resourceName);

    Result getDeployments();

    //删除流程定义
    Result delDefinition(String depID,String pdID);
}
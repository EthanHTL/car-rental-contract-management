package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysResource;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface SysResourceService extends DbService<SysResource> {


    Result uploadFiles(MultipartFile[] files);

    Result<PageInfo<SysResource>> findContractTemplatePage(SysResource sysResource);

    Result createContract(SysResource sysResource);

    Result updateContractTemplate(SysResource sysResource);
}
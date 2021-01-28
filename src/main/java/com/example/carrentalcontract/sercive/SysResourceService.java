package com.example.carrentalcontract.sercive;

import com.example.carrentalcontract.common.DbService;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysResource;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysResourceService extends DbService<SysResource> {


    Result uploadFiles(MultipartFile[] files);

    Result<PageInfo<SysResource>> findContractTemplatePage(SysResource sysResource);

    Result createContract(SysResource sysResource);

    Result updateContractTemplate(SysResource sysResource);

    Result<PageInfo<SysResource>> findPage(SysResource sysResource);

    Result<SysResource> selectByPrimaryKey(Long id);

    Result<SysResource> updateResource(SysResource sysResource);

    Result<SysResource> deleteResource(SysResource sysResource);
}
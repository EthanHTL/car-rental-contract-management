package com.example.carrentalcontract.sercive.impl;

import com.example.carrentalcontract.common.DbServiceImpl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysResource;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.mapper.SysResourceMapper;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.example.carrentalcontract.util.BASE64DecodedMultipartFile;
import com.example.carrentalcontract.util.FileHandler;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-08 16:45
 **/
@Service("sysResourceService")
public class SysResourceServiceImpl extends DbServiceImpl<SysResource> implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public Result uploadFiles(MultipartFile[] files) {
        List<SysResource> resources = null;
        try {
            resources = FileHandler.transferFiles(files);
        } catch (FileNotFoundException e) {
            return new Result(901, "文件没找到");
        } catch (IOException e) {
            return new Result(902, "文件上传失败");

        }
        // 将信息插入数据库
        return super.insertBatch(resources);

    }

    @Override
    public Result<PageInfo<SysResource>> findContractTemplatePage(SysResource sysResource) {
        List<SysResource> contractPage = sysResourceMapper.findContractTemplatePage(sysResource);
        PageInfo info = new PageInfo(contractPage);
        return new Result<>(info);

    }

    @Override
    public Result<PageInfo<SysResource>> findContractWholeTemplatePage(SysResource resource) {
        List<SysResource> contractPage = sysResourceMapper.findContractPage(resource,resource.getPageNum(),resource.getPageSize());
        PageInfo info = new PageInfo(contractPage);
        return new Result<>(info);
    }

    @Override
    public Result<SysResource> selectByPrimaryKey(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Result<SysResource> deleteResource(SysResource sysResource) {
        return super.destroy(sysResource);
    }

    @Override
    public Result<SysResource> updateResource(SysResource sysResource) {
        return null;
    }

    @Override
    public Result<PageInfo<SysResource>> findPage(SysResource sysResource) {
        return null;
    }

    @Override
    public Result updateContractTemplate(SysResource sysResource) {
        return super.update(sysResource);
    }

    @Override
    public Result createContract(SysResource sysResource) {
        String path = sysResource.getPath();
        String uploadPath = "";
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(path);
        try {
            uploadPath = FileHandler.singleFileUpload(file);
            sysResource.setPath(uploadPath);
            // sysResource.setDictType(7);
        } catch (IOException e) {
            return new Result(901,"图片错误");
        }
        return super.insert(sysResource);
    }
}

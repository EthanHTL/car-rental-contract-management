package com.example.carrentalcontract.controller;


import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.FileEnum;
import com.example.carrentalcontract.entity.model.Contract;
import com.example.carrentalcontract.entity.model.SysResource;
import com.example.carrentalcontract.sercive.ContractService;
import com.example.carrentalcontract.sercive.SysResourceService;
import com.example.carrentalcontract.util.FileHandler;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 合同表(Contract)表控制层
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@RestController
@RequestMapping("/api/v1/car/contract")
@Slf4j
public class ContractController {
    /**
     * 服务对象
     */
    @Resource
    private ContractService contractService;
    @Resource
    private SysResourceService sysResourceService;

    /**
     * 分页
     *
     * @param contract
     * @return
     */
    @PostMapping("/find/page")
    public Result<PageInfo<Contract>> findPage(@RequestBody Contract contract) {
        return this.contractService.findPage(contract);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @PostMapping("/find/all")
    public Result<List<Contract>> findAll() {
        UserDetails userInfo = getUserInfo();
        return this.contractService.findAll();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("files") MultipartFile[] files) {
        FileHandler fileHandler = new FileHandler();
        List<SysResource> resources = fileHandler.transferFiles(files);
        // 将信息插入数据库

        return sysResourceService.insertBatch(resources);
    }


    public UserDetails getUserInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
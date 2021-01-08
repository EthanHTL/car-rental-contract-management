package com.example.carrentalcontract.util;

import com.example.carrentalcontract.entity.en.FileEnum;
import com.example.carrentalcontract.entity.model.SysResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-08 16:42
 **/
public class FileHandler {

    public List<SysResource> transferFiles(MultipartFile[] files) {
        List<SysResource> resources = new ArrayList<>();

        for (MultipartFile file : files) {
            SysResource resource = new SysResource();
            // 上传简单文件名
            String oldFileName = file.getOriginalFilename();
            String description = oldFileName.substring(0, oldFileName.indexOf('.'));
            // 获取文件后缀
            String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
            // 生成新的文件名称
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    UUID.randomUUID().toString().replace("-", "").substring(6) + extension;
            // 文件的大小
            Long size = file.getSize();
            // 文件类型
            String type = file.getContentType();
            // 处理根据日期生成目录
            String realPath = null;
            try {
                realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 上传文件的路径
            String dataDirPath = realPath + "/" + FileEnum.PROFILE_PHOTO.getCode();
            // switch (type){
            //
            // }

            File dateDir = new File(dataDirPath);
            if (!dateDir.exists()) {
                dateDir.mkdirs();
            }
            // 处理文件上传
            try {
                file.transferTo(new File(dateDir, newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            resource.setOldFilename(oldFileName).setNewFilename(newFileName).setExt(extension)
                    .setSize(String.valueOf(size)).setType(Long.valueOf(type))
                    .setPath(dataDirPath).setDescription(description);

            resources.add(resource);
        }
        return resources;
    }

}

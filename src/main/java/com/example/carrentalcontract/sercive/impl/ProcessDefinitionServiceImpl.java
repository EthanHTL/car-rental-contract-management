package com.example.carrentalcontract.sercive.impl;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.mapper.FlowMapper;
import com.example.carrentalcontract.sercive.ProcessDefinitionService;
import com.example.carrentalcontract.util.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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


@Service("processDefinitionService")
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 长传并部署
     * @param multipartFile
     * @return
     */
    @Override
    public Result uploadStreamAndDeployment(MultipartFile multipartFile) {
        // 获取上传的文件名
        String fileName = multipartFile.getOriginalFilename();

        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = multipartFile.getInputStream();

            // 文件的扩展名
            String extension = FilenameUtils.getExtension(fileName);

            // ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();//创建处理引擎实例
            // repositoryService = processEngine.getRepositoryService();//创建仓库服务实例

            Deployment deployment = null;
            if (extension.equals("zip")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = repositoryService.createDeployment()//初始化流程
                        .addZipInputStream(zip)
                        .name("流程部署名称可通过接口传递现在写死")
                        .deploy();
            } else {
                deployment = repositoryService.createDeployment()//初始化流程
                        .addInputStream(fileName, fileInputStream)
                        .name("流程部署名称可通过接口传递现在写死")
                        .deploy();
            }

            return Result.success(deployment.getId()+";"+fileName);

        } catch (Exception e) {
            return new Result(901,"部署流程失败");

        }
        //return AjaxResponse.AjaxData(1,"成功",fileName);

    }

    /**
     * 上传
     * @param request
     * @param multipartFile
     * @return
     */
    @Override
    public Result upload(HttpServletRequest request, MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = multipartFile.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "file:D:\\WangJianIDEA_Test\\activiti-imooc\\src\\main\\resources\\resources\\bpmn\\"; // 上传后的路径

        //本地路径格式转上传路径格式
        filePath = filePath.replace("\\", "/");
        filePath = filePath.replace("file:", "");

        // String filePath = request.getSession().getServletContext().getRealPath("/") + "bpmn/";
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File file = new File(filePath + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return Result.success(fileName);
    }

    @Override
    public Result addDeploymentByFileNameBPMN(String deploymentFileUUID,String deploymentName) {
        try {
            String filename = "resources/bpmn/" + deploymentFileUUID;
            Deployment deployment = repositoryService.createDeployment()//初始化流程
                    .addClasspathResource(filename)
                    .name(deploymentName)
                    .deploy();
            //System.out.println(deployment.getName());
            return Result.success(deployment.getId());

        } catch (Exception e) {
            return new Result(901,"BPMN部署流程失败");
        }

    }

    @Override
    public Result addDeploymentByString(String stringBPMN) {
        try {
            Deployment deployment = repositoryService.createDeployment()
                    .addString("CreateWithBPMNJS.bpmn",stringBPMN)
                    .name("不知道在哪显示的部署名称")
                    .deploy();
            //System.out.println(deployment.getName());
            return Result.success(deployment.getId());
        } catch (Exception e) {
            return new Result(901,"string部署流程失败");
        }
    }

    @Override
    public Result getDefinitions() {

        try {
            List<HashMap<String, Object>> listMap= new ArrayList<HashMap<String, Object>>();
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();

            list.sort((y,x)->x.getVersion()-y.getVersion());

            for (ProcessDefinition pd : list) {
                HashMap<String, Object> hashMap = new HashMap<>();
                //System.out.println("流程定义ID："+pd.getId());
                hashMap.put("processDefinitionID", pd.getId());
                hashMap.put("name", pd.getName());
                hashMap.put("key", pd.getKey());
                hashMap.put("resourceName", pd.getResourceName());
                hashMap.put("deploymentID", pd.getDeploymentId());
                hashMap.put("version", pd.getVersion());
                listMap.add(hashMap);
            }
            return Result.success(listMap);

        }catch (Exception e) {
            return new Result(901,"获取流程定义失败");

        }
    }

    //获取流程定义XML
    @Override
    public void getProcessDefineXML(HttpServletResponse response,
                                    String deploymentId,
                                    String resourceName) {
        try {
            InputStream inputStream = repositoryService.getResourceAsStream(deploymentId,resourceName);
            int count = inputStream.available();
            byte[] bytes = new byte[count];
            response.setContentType("text/xml");
            OutputStream outputStream = response.getOutputStream();
            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            inputStream.close();
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public Result getDeployments() {
        try {
            List<HashMap<String, Object>> listMap= new ArrayList<HashMap<String, Object>>();
            List<Deployment> list = repositoryService.createDeploymentQuery().list();
            for (Deployment dep : list) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", dep.getId());
                hashMap.put("name", dep.getName());
                hashMap.put("deploymentTime", dep.getDeploymentTime());
                listMap.add(hashMap);
            }

            return Result.success(listMap);
        } catch (Exception e) {
            return new Result(901,"查询失败");
        }
    }

    //删除流程定义
    @Override
    public Result delDefinition(String depID, String pdID) {
        try {

            //删除数据
            // int result = mapper.DeleteFormData(pdID);
            repositoryService.deleteDeployment(depID, true);
            return new Result(200,"删除成功");

        } catch (Exception e) {
            return new Result(901,"删除失败");
        }
    }

}
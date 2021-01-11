package com.example.carrentalcontract.entity.response;

import com.example.carrentalcontract.entity.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @Author admin
 * @Description //TODO
 * @date 2021/1/3 17:58
 * @Version 1.0
 **/
@Data
public class SysRoleResponseInfo extends SysRole {

    private List<SysMenuResponseInfo> menuResponseInfos;

    private List<SysApiResponseInfo> apiResponseInfos;

}

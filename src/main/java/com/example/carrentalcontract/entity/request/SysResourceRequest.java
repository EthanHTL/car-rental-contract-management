package com.example.carrentalcontract.entity.request;

import com.example.carrentalcontract.entity.model.SysResource;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-28 13:47
 **/
@Data
public class SysResourceRequest extends SysResource {

    /**
     * 查询类型列表
     */
    private List<String> types;
}

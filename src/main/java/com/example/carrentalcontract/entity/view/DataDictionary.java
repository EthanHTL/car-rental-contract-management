package com.example.carrentalcontract.entity.view;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据字典(DataDictionary)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class DataDictionary extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -52759648939749130L;
    /**
    * 主键
    */
    private Long typeCode;
    /**
    * 配置项名字
    */
    private String info;
    /**
    * 类型名称
    */
    private String typeName;
    /**
    * 业务代码
    */
    private String dictcode;



}
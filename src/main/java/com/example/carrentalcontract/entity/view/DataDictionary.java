package com.example.carrentalcontract.entity.view;

import java.io.Serializable;

/**
 * 数据字典(DataDictionary)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
public class DataDictionary implements Serializable {
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


    public Long getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDictcode() {
        return dictcode;
    }

    public void setDictcode(String dictcode) {
        this.dictcode = dictcode;
    }

}
package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程表(Flow)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
public class Flow extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -42676884829400034L;
    /**
    * 流程编号，主键
    */
    private Long flowId;
    /**
    * 流程号，惟一列
    */
    private Long flowNo;
    /**
    * 流程名称
    */
    private String flowName;
    /**
    * 备注
    */
    private String remark;

}
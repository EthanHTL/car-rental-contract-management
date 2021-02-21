package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典(DataDictionary)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:56
 */
@Data
@Table(name = "sys_dict")
public class SysDict extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = -52759648939749130L;

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
    * 编码
    */
    private String code;
    /**
    * 配置项名称
    */
    private String name;
    /**
    * 描述
    */
    private String descript;

    /**
     * 标识
     */
    private Integer flag;

    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private Date updateTime;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;




}
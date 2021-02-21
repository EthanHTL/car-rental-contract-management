package com.example.carrentalcontract.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "sys_dict_detail")
public class SysDictDetail {

    @Id
    private Long id;

    /**
     * 字典编码
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;
    private Integer flag;
}

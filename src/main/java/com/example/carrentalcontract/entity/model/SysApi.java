package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.annotation.Dict;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 权限表(SysApi)实体类
 *
 * @author makejava
 * @since 2021-01-05 17:24:12
 */
@Data
@Table(name = "sys_api")
public class SysApi implements Serializable {
    private static final long serialVersionUID = 703134571616434232L;
    /**
    * 接口权限主键
    */
    private Long id;
    /**
    * 父节点
    */
    @Column(name = "api_pid")
    private Long apiPid;
    /**
    * 当前接口所有父节点
    */
    @Column(name = "api_pids")
    private String apiPids;
    /**
    * 0：不是子节点，1，是子节点
    */
    @Column(name = "is_leaf")
    @Dict(dictCode = "is_leaf")
    private Integer isLeaf;
    /**
    * 接口名
    */
    @Column(name = "api_name")
    private String apiName;
    /**
    * 路径
    */
    private String url;
    /**
    * 排序
    */
    private Integer sort;
    /**
    * 菜单层级
    */
    private Integer level;
    /**
    * 是否禁用：1(启用) 0(禁用)
    */
    private Integer flag;
    /**
    * 创建时间
    */
    @Column(name = "create_time")
    private Date createTime;
    /**
    * 修改时间
    */
    @Column(name = "update_time")
    private Date updateTime;
    /**
    * 创建人
    */
    @Column(name = "creator_id")
    private String creatorId;
    /**
    * 修改人
    */
    @Column(name = "updator_id")
    private String updatorId;

}
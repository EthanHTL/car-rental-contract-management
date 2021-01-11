package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * (SysMenu)实体类
 * @since 2021-01-05 14:58:20
 */
@Data
@Table(name = "sys_menu")
public class SysMenu extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = 804335283103992396L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 父节点
    */
    @Column(name = "menu_pid")
    private Long menuPid;
    /**
    * 当前菜单所有父菜单
    */
    @Column(name = "menu_pids")
    private String menuPids;
    /**
    * 0：不是子节点，1，是子节点
    */
    @Column(name = "is_leaf")
    private Integer isLeaf;
    /**
    * 菜单名
    */
    @Column(name = "menu_name")
    private String menuName;
    /**
    * 路径
    */
    private String url;
    /**
    * 图标
    */
    private String icon;
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
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "creator_id")
    private String updatorId;

    @Column(name = "updator_id")
    private String creatorId;



}
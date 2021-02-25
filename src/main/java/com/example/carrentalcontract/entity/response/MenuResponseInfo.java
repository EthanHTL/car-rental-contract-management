package com.example.carrentalcontract.entity.response;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-02-25 17:28
 **/
@Data
public class MenuResponseInfo {
    /**
     * 路由路径
     */
    private String path;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<MenuResponseInfo> children;

    private Integer sort;

}

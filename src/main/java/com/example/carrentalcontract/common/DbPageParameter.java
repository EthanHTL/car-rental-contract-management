package com.example.carrentalcontract.common;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.persistence.Transient;

public class DbPageParameter extends DbLimitParameter  {
    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String dynamicTableName;
    @Transient
    private Integer beginIndex;
    @Transient
    private Integer total;

    public DbPageParameter() {
    }

    public String getDynamicTableName() {
        return this.dynamicTableName;
    }

    public void setDynamicTableName(String dynamicTableName) {
        this.dynamicTableName = dynamicTableName;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getBeginIndex() {
        return this.beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}


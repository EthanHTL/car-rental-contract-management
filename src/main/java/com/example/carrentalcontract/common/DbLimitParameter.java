package com.example.carrentalcontract.common;

import javax.persistence.Transient;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-28 16:03
 **/
public class DbLimitParameter {
    @Transient
    private Integer maxSize = 2000;

    public DbLimitParameter() {
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
}

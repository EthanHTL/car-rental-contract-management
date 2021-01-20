package com.example.carrentalcontract.pojo;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 出差申请中的流程变量对象
 */
@Data
@ToString
@Accessors(chain = true)
public class Evection implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 出差单的名字
     */
    private String evectionName;

    /**
     * 出差天数
     */
    private Double num;

    /**
     * 出差开始时间
     */
    private Data beginDate;
    /**
     * 出差结束时间
     */
    private Data endDate;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出差原因
     */
    private String reason;
}

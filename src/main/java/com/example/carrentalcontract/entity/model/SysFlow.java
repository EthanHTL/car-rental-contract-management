package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class SysFlow extends DbPageParameter implements Serializable {

    @Id
    private Long id;
    @Column(name = "flow_name")
    private String flowName;

    @Column(name = "flow_key")
    private String flowKey;
    @Column(name = "filepath")
    private String filePath;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 1 - 没有部署， 0 - 已经部署
     */
    private Integer state;


}

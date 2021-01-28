package com.example.carrentalcontract.entity.en;

public enum CheckEnum {

    // 已通过
    PENDING(1,"待审核"),
    NOT_PASS(2,"审核未通过"),
    PASS(3,"审核通过"),
    ;


    private Integer status;
    private String message;


    CheckEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

}

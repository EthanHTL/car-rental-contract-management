package com.example.carrentalcontract.entity.en;

public enum ContractEnum {

    // 已通过
    ALREADY_PASSED(1),
    NOT_PASS(2);


    private Integer status;

    private ContractEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}

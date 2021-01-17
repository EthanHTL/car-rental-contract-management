package com.example.carrentalcontract.entity.en;

public enum FileEnum {

    ;
    // 编号
    private String code;

    // 类型
    private String type;

    // 描述，备注
    private String desc;

    FileEnum(String code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}

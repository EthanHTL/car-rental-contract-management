package com.example.carrentalcontract.entity.en;

public enum FileEnum {

    PROFILE_PHOTO("sys_head_img","头像");

    private String code;

    private String name;

    FileEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

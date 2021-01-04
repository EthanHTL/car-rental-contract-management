package com.example.carrentalcontract.entity.en;

public enum UserEnum {
    SUCCESS(200, "成功"),
    NOT_LOGIN(601, "未登录"),
    UNAUTHORIZED(602, "未授权"),
    USER_READY(603, "账户已存在"),
    AUTHORIZED_EXPIRES(604,"授权过期"),
    ACCOUNT_NOT_EXIST(605,"账户不存在"),
    ACCOUNT_LOCKED(606,"账户被锁定")
    ;

    // 用户状态
    private Integer statusCode;
    // 信息
    private String message;


    UserEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}

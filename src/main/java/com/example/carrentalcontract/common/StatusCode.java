package com.example.carrentalcontract.common;


public class StatusCode {
    public static int SUCCESS = 200;
    // 暂停服务
    public static int SERVICE_PAUSED = 800;
    // 服务请求超时
    public static int SERVICE_REQUEST_TIMEOUT = 801;
    // 服务使用者错误
    public static int SERVICE_CONSUMER_ERROR = 802;
    // 务提供商错误
    public static int SERVICE_PROVIDER_ERROR = 803;
    // 未找到服务节点
    public static int SERVICE_NODE_NOT_FUND = 804;

    public StatusCode() {

    }
}
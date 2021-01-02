package com.example.carrentalcontract.exception;

import lombok.Data;

/**
 * @ClassName BaseException
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 15:34
 * @Version 1.0
 **/
@Data
public class BaseException extends RuntimeException{
    //状态码
    private Integer statusCode;

    //消息
    private String message;
    public BaseException() {
        super();
    }


    public BaseException(Integer statusCode,String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }


}

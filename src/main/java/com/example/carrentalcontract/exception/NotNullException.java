package com.example.carrentalcontract.exception;

/**
 * @ClassName NotNullException
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 15:34
 * @Version 1.0
 **/
public class NotNullException extends BaseException{

    public NotNullException (Integer statusCode, String message) {
        super(statusCode, message);
    }
}

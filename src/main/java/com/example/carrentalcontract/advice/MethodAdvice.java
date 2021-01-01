package com.example.carrentalcontract.advice;

import com.example.carrentalcontract.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName MethodAdvice
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 1:42
 * @Version 1.0
 **/
@ControllerAdvice
public class MethodAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> getErrorAndReturn(Exception e){
        Result<Object> objectResult = new Result<>();
        objectResult.setStatusCode(200);
        objectResult.setErrorStackTrace(e.getStackTrace().toString());
        objectResult.setMessage(e.getMessage());
        return objectResult;
    }
}

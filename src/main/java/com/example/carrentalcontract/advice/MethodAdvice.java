package com.example.carrentalcontract.advice;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.exception.NotNullException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 1:42
 * @Version 1.0
 **/
@RestControllerAdvice
public class MethodAdvice {



    @ExceptionHandler(NotNullException.class)
    public Result<Object> getErrorAndReturn(NotNullException e){
        Result<Object> objectResult = new Result<>();
        objectResult.setStatusCode(e.getStatusCode());
        objectResult.setErrorStackTrace(e.getStackTrace().toString());
        objectResult.setMessage(e.getMessage());
        return objectResult;
    }

    
}

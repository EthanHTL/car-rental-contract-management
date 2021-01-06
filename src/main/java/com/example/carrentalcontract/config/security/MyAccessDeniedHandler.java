package com.example.carrentalcontract.config.security;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.ResponseCode;
import com.example.carrentalcontract.util.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-06 09:53
 **/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request
            , HttpServletResponse response
            , AccessDeniedException e) throws IOException, ServletException {
        ServletUtil.printRestResult(Result.error(ResponseCode.WEB_403));
    }
}

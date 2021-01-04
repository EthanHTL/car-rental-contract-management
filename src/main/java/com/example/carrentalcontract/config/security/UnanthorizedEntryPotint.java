package com.example.carrentalcontract.config.security;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.ResponseCode;
import com.example.carrentalcontract.util.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UnanthorizedEntryPotint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        ServletUtil.printRestResult(Result.error(ResponseCode.WEB_401));
    }
}

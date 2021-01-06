package com.example.carrentalcontract.config.security;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.en.ResponseCode;
import com.example.carrentalcontract.entity.request.LoginUser;
import com.example.carrentalcontract.entity.request.MyUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author admin
 * @Description //TODO
 * @date 2021/1/6 1:06
 * @Version 1.0
 **/
@Component
@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Value("${spring.security.loginType}")
    private String loginType;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();

            LoginUser loginUser = new LoginUser(principal.getUsername(),request.getSession().getId());

            log.info("登录用户的token：{}", loginUser);
            // 登录成功将用户信息和权限返回
            response.getWriter().write(objectMapper.writeValueAsString(
                    new Result(ResponseCode.LOGIN_SUCCESS.getCode(),
                            ResponseCode.LOGIN_SUCCESS.getMsg(), loginUser
                    )));
        } else {
            // 会帮我们返回上一次请求的页面
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}

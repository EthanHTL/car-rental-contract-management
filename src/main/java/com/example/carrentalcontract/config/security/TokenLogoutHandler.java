package com.example.carrentalcontract.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 退出处理器
 * @author: 黄天亮
 * @create: 2021-01-04 10:51
 **/
public class TokenLogoutHandler implements LogoutHandler {

    private JwtTokenManager jwtTokenManager;

    public TokenLogoutHandler(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 1 从header里面获取token
        // 2 token不为空，移除token
        String token = request.getHeader("token");
        if (token != null){
            // 移除
            // jwtTokenManager.(token);
        }
        // 输出
        // ResponseUtil.out(request, Result.success());
    }
}

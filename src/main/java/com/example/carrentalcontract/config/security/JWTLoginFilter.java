package com.example.carrentalcontract.config.security;

import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.Users;
import com.example.carrentalcontract.entity.request.UserPermissionRequest;
import com.example.carrentalcontract.util.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenManager jwtTokenManager;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            Users user = new ObjectMapper().readValue(req.getInputStream(), Users.class);
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // 2 认证成功调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 认证成功，得到认证成功之后用户信息
        UserPermissionRequest user = (UserPermissionRequest) authResult.getPrincipal();
        // 根据用户生成token
        jwtTokenManager.generateToken(user);
        // 把用户名称和用户权限放到Redis

        ServletUtil.printRestResult(Result.success());
    }
}

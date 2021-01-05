package com.example.carrentalcontract.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author admin
 * @Description //TODO
 * @date 2021/1/5 23:54
 * @Version 1.0
 **/
@Component("rbacService")
@Slf4j
public class MyRBACService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            // 本次访问的资源
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(request.getRequestURI());
            log.debug("simpleGrantedAuthority:{}",simpleGrantedAuthority);
            log.debug("userDetails.getAuthorities():{}",userDetails.getAuthorities());

            // userDetails.getAuthorities() 中包含所有的请求
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);

        }
        return false;
    }
}

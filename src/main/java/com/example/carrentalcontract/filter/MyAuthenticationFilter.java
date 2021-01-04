package com.example.carrentalcontract.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-04 17:34
 **/
public class MyAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("请求头类型： " + request.getContentType());
        // if ((request.getContentType() == null && request.getContentLength() > 0) || (request.getContentType() != null &&
        //         !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE))) {
        //     filterChain.doFilter(request, response);
        //     return;
        // }
    }
}

package com.example.carrentalcontract.util;

import com.example.carrentalcontract.entity.model.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-04 13:42
 **/
public class SessionUtil {
    //得到当前登录的用户名
    public static String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }
    //得到当前登录的用户
    public static Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                Users currentuser = (Users) principal;
                return currentuser;
            }
            return null;
        }
        return null;
    }
}

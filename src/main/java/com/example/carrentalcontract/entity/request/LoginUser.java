package com.example.carrentalcontract.entity.request;

import com.example.carrentalcontract.entity.model.SysUser;
import lombok.Data;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-06 12:03
 **/
@Data
public class LoginUser   {
    private String username;
    private String token;

    public LoginUser() {
    }

    public LoginUser(String username, String token) {
        this.username = username;
        this.token = token;
    }
}

package com.example.carrentalcontract.config;

import com.alibaba.fastjson.JSON;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.config.security.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-28 14:22
 **/
@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Resource
    private MyUserDetailsService userDetailsService;
    @Resource
    private MyAuthenticationFailureHandler failureHandler;
    @Resource
    private MyAuthenticationSuccessHandler successHandler;
    @Resource
    private MyAccessDeniedHandler accessDeniedHandler;
    @Resource
    private UnanthorizedEntryPotint unanthorizedEntryPotint;

    // 创建配置类，设置使用哪个userDetailsService 实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置没有权限访问处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unanthorizedEntryPotint);

        // 退出
        http.logout().logoutUrl("/logout").permitAll();

        http.formLogin() // 自定义自己编写的登录页面
                .usernameParameter("username")
                .passwordParameter("password")
                // .loginPage("/login")
                .loginProcessingUrl("/user/login") // 登录访问路径
                .successHandler(successHandler)
//                .defaultSuccessUrl("/success")
                .failureHandler(failureHandler)
                .and().authorizeRequests()
                .antMatchers("/","/user/login","/api/v1/car/users/register").permitAll() // 设置哪些路径不需要认证，可以直接访问
                // .antMatchers("/api/v1/**").access("@rbacService.hasPermission(request,authentication)")
                .anyRequest().permitAll()
                // 记住我
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .rememberMeParameter("remember-me-new") // 自定义 前端input name属性名
                .rememberMeCookieName("remember-me-cookie") // 自定义前端储存cookie的名字
                .tokenValiditySeconds(20) // 设置有效时长，单位 秒
                .userDetailsService(userDetailsService())
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .and().csrf().disable(); // 关闭 csrf 防护

    }


    /**
     * 密码
     */
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我操作
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 创建数据库
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


}

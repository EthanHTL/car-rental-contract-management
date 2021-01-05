package com.example.carrentalcontract.config;

import com.alibaba.fastjson.JSON;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.config.security.MyUserDetailsService;
import com.example.carrentalcontract.config.security.UnanthorizedEntryPotint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Resource
    private MyUserDetailsService userDetailsService;

    // 创建配置类，设置使用哪个userDetailsService 实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置没有权限访问处理
        http.exceptionHandling().authenticationEntryPoint(new UnanthorizedEntryPotint());
        // 退出
        http.logout().logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功...");
                    out.flush();
                 }).permitAll();

        http.formLogin() // 自定义自己编写的登录页面
                .usernameParameter("username")
                .passwordParameter("password")
                //.loginPage("/login") // 登录页面设置
                .loginProcessingUrl("/user/login") // 登录访问路径
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        out = response.getWriter();
                        out.println(JSON.toJSONString(request.getCookies()));
                        // String resp = JSON.toJSONString(rest);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        Result result;

                        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                            result = Result.error(e.getMessage());
                        } else if (e instanceof LockedException) {
                            result = Result.error("账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            result = Result.error("证书过期，请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            result = Result.error("账户过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            result = Result.error("账户被禁用，请联系管理员!");
                        } else {
                            log.error("登录失败：", e);
                            result = Result.error("登录失败!");
                        }
                        PrintWriter out = null;
                        try {
                            response.setCharacterEncoding("UTF-8");
                            response.setContentType("application/json");
                            out = response.getWriter();
                            out.println(JSON.toJSONString(result));
                        } catch (Exception ex) {
                            log.error(ex + "输出JSON出错");
                        } finally {
                            if (out != null) {
                                out.flush();
                                out.close();
                            }
                        }

                    }
                })
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll() // 设置哪些路径不需要认证，可以直接访问
                .antMatchers("/api/v1/car/contract/find/page").hasRole("sale,admin")
                // .antMatchers("/api/v1/**").hasAuthority("sale,admin")
                .anyRequest().permitAll()
                // 记住我
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(20) // 设置有效时长，单位 秒
                .userDetailsService(userDetailsService())
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

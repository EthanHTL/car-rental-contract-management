package com.example.carrentalcontract.config;

import com.alibaba.fastjson.JSON;
import com.example.carrentalcontract.entity.en.UserEnum;
import com.example.carrentalcontract.entity.model.Users;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.example.carrentalcontract.config.security.UnanthorizedEntryPotint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-28 14:22
 **/
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    // 创建配置类，设置使用哪个userDetailsService 实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(password());
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
                .usernameParameter("account")
                .passwordParameter("password")
                //.loginPage("/login") // 登录页面设置
                .loginProcessingUrl("/user/login") // 登录访问路径
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        // String resp = JSON.toJSONString(rest);
                        String cookies = JSON.toJSONString(request.getCookies()[0]);

                        response.getWriter().write(cookies);
                    }
                })
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll() // 设置哪些路径不需要认证，可以直接访问
                .antMatchers("/api/v1/car/contract/find/page").hasRole("sale")
                .anyRequest().permitAll()
                // 记住我
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60) // 设置有效时长，单位 秒
                .userDetailsService(userDetailsService())
                .and().csrf().disable(); // 关闭 csrf 防护

    }


    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 创建数据库
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    //
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            UsersMapper userMapper;
            @Override
            public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
                Users loadUser = new Users();
                loadUser.setAccount(account);

                Users user = userMapper.selectOne(loadUser);
                if (user == null) {// 数据库没有用户名，认证失败
                    throw new UsernameNotFoundException(UserEnum.ACCOUNT_NOT_EXIST.getMessage());
                }
                // 获取用户权限
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");

                return new User(user.getAccount() ,user.getPassword(),true,true,true,true, auths);
            }
        };
    }


}

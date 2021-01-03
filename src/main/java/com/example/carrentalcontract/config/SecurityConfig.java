package com.example.carrentalcontract.config;

import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.mapper.UsersMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
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
    private  static ObjectMapper objectMapper = new ObjectMapper();

    // 创建配置类，设置使用哪个userDetailsService 实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("auth====>"+auth);
        UserDetailsService detailsService = userDetailsService();
        auth.userDetailsService(detailsService).passwordEncoder(password());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置没有权限访问跳转自定义页面
        //http.exceptionHandling().accessDeniedPage("/unauth");
        // 退出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();

        http.formLogin() // 自定义自己编写的登录页面
                .usernameParameter("username")
                .passwordParameter("password")
                //.loginPage("/login") // 登录页面设置
                .loginProcessingUrl("/user/login") // 登录访问路径
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(request.getCookies().toString());
                    }
                })
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll() // 设置哪些路径不需要认证，可以直接访问
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

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            UsersMapper userMapper;

            @Override
            public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
                Users loadUser = new Users();
                loadUser.setCode(code);

                Users user = userMapper.selectOne(loadUser);
                if (user == null) {// 数据库没有用户名，认证失败
                    throw new UsernameNotFoundException("用户名不存在");
                }
                // 获取用户权限
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");

                return new User(user.getUserName() ,user.getPassword(),true,true,true,true, auths);
            }
        };
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     // 配置url的访问权限
    //     http.authorizeRequests()
    //             .antMatchers("/").permitAll()
    //             .antMatchers("/**update**").permitAll()
    //             .antMatchers("/login/**").permitAll()
    //             .anyRequest().authenticated();
    //
    //     // 关闭csrf保护功能
    //     // http.csrf().disable();
    //
    //     // 使用自定义的登录窗口
    //     http.formLogin() // 自定义自己编写的登录页面
    //             .loginPage("/userLogin").permitAll() // 登录页面设置
    //             .usernameParameter("username").passwordParameter("password")
    //             .defaultSuccessUrl("/")
    //             .failureUrl("/userLogin?error");
    //
    // }

}

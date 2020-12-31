package com.example.carrentalcontract.security;

import com.example.carrentalcontract.entity.view.Users;
import com.example.carrentalcontract.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
        // 配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        // 退出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        http.formLogin() // 自定义自己编写的登录页面
                .loginPage("/login.html") // 登录页面设置
                .loginProcessingUrl("/user/login") // 登录访问路径
                .defaultSuccessUrl("/success.html").permitAll() // 登录成功之后，跳转路径
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll() // 设置哪些路径不需要认证，可以直接访问
                // 1. hasAuthority
                // .antMatchers("/test/index").hasAuthority("admins") //  当前登录用户，只有具有admins权限才可以访问这个路径
                // 2. hasAnyAuthority
                // .antMatchers("/test/index").hasAnyAuthority("admins,manager") //  如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true
                // 3.hasRole
                // .antMatchers("/test/index").hasRole("sale") //  如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true
                // 4.hasRole
                // .antMatchers("/test/index").hasAnyRole("sale") //  如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true
                .anyRequest().authenticated()
                // 记住我
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(600) // 设置有效时长，单位 秒
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
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

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
                // if (user==null){
                return new org.springframework.security.core.userdetails.User(null, null, false, false, false, false, null);
                // }
                // return new org.springframework.security.core.userdetails.User(code,user.getPassword(),true,true,true,true,translatePowerToGrantedAuthority(user.getPowers()));
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

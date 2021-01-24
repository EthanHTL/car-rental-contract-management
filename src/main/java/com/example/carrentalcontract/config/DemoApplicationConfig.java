package com.example.carrentalcontract.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class DemoApplicationConfig {
    /**
     * 添加security的用户
     */
    @Bean
    public UserDetailsService myUserDetailsService() {
        //    把用户存储在内存中
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        //    构建用户的信息
        String[][] userGroupAndRoles = {
                {"jack", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"rose", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"tom", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"jerry", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"other", "password", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
                {"system", "password", "ROLE_ACTIVITI_USER"},
                {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
        };
        for (String[] users : userGroupAndRoles) {
            //  用户的角色和组
            List<String> authStr = Arrays.asList(Arrays.copyOfRange(users, 2, users.length));
            log.info(">Registering new user:" + users[0] + " with the following Authorities[" + authStr + "]");
            inMemoryUserDetailsManager.createUser(new User(users[0]
                    , password().encode(users[1])
                    , authStr.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
            );
        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}

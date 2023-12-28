package com.github.ahisyfa.saad.saad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: BasicSecurityConfiguration.java, v 0.1 2023-07-23  07.04 Ahmad Isyfalana Amin Exp $
 */
@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration {

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("admin")
                .password("adminsaad")
                .authorities("read,write")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
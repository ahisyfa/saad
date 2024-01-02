package com.github.ahisyfa.saad.saad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: BasicSecurityConfiguration.java, v 0.1 2023-07-23  07.04 Ahmad Isyfalana Amin Exp $
 */
@Configuration
@EnableWebSecurity
public class SaadSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CORS Config
            .cors(Customizer.withDefaults()) // Enable CORS

            // CSRF Config
            .csrf(csrf -> csrf.disable())    // Disable CSRF

            // Login Form
            .formLogin(Customizer.withDefaults())

            // Authentication Config
            .authorizeHttpRequests(request -> {
                request.anyRequest().authenticated();
            })

            // Enable H2 Console
            .headers(headers -> {
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
            });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
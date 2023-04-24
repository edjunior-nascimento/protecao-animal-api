package com.api.protecaoanimal.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.GET, "/acoes/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/acoes").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/acoes/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
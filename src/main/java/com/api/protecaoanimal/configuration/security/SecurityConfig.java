package com.api.protecaoanimal.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic()
                .and()
            .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //.loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout") // Definindo a URL de logout
                .logoutSuccessUrl("/login") // Definindo a página de sucesso após logout
                .invalidateHttpSession(true) // Invalidando a sessão do usuário
                .deleteCookies("JSESSIONID") // Deletando o cookie da sessão
                .and()
            .csrf().disable();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

package com.api.protecaoanimal.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private CustomUserDetailsService authenticationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        UserDetails userDetails = authenticationService.loadUserByUsername(login);

        if (!passwordEncoder.matches(senha, userDetails.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        return new UsernamePasswordAuthenticationToken(login, senha, userDetails.getAuthorities());        
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

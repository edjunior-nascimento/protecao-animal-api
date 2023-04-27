// package com.api.protecaoanimal.configuration.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomAuthenticationProvider implements AuthenticationProvider {
    
//     @Autowired
//     private CustomUserDetailsService authenticationService;

//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         String login = authentication.getName();
//         String senha = authentication.getCredentials().toString();

//         UserDetails userDetails = authenticationService.loadUserByUsername(login);

//         if (!senha.equals(userDetails.getPassword())) {
//             throw new BadCredentialsException("Senha Inválida");
//         }

//         return new UsernamePasswordAuthenticationToken(login, senha, userDetails.getAuthorities());        
        
//     }

//     @Override
//     public boolean supports(Class<?> authentication) {
//         return authentication.equals(UsernamePasswordAuthenticationToken.class);
//     }
// }

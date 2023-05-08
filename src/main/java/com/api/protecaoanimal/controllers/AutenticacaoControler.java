package com.api.protecaoanimal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.protecaoanimal.dtos.LoginFormDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Autenticação")
public class AutenticacaoControler {


    @PostMapping("/login")
    public String loginPost(LoginFormDto loginFormDto) {
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/info")
    public ResponseEntity<Authentication> getLoggedInUser(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }


    
    
}

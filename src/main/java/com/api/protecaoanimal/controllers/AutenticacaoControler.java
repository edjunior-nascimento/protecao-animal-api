package com.api.protecaoanimal.controllers;

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


    
    
}

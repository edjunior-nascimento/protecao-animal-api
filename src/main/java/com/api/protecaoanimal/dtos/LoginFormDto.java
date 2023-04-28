package com.api.protecaoanimal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginFormDto {
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}

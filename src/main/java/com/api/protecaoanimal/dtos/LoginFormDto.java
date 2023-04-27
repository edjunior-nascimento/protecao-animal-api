package com.api.protecaoanimal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "Login")
public class LoginFormDto {
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}

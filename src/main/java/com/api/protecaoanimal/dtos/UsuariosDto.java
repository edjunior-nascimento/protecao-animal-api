package com.api.protecaoanimal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuariosDto {
    
    @NotBlank
    private String login;

    @NotBlank
    private String senha;

}

package com.api.protecaoanimal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegrasDto {
    
    @NotBlank
    private String nome;

}

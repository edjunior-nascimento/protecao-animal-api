package com.api.protecaoanimal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "regras")
public class RegrasDto {
    
    @NotBlank
    private String nome;

}

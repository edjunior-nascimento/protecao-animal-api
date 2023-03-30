package com.api.protecaoanimal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "situacoes")
public class SituacoesDto {

    @NotBlank
    @Size(max = 50 ,message = "Campo nome não pode ser maior que 50 caracteres")
    private String nome;

    @Size(max = 255 ,message = "Campo descricao não pode ser maior que 255 caracteres")
    private String descricao;
    
}

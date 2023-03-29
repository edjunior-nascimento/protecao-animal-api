package com.api.protecaoanimal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "parceiros")
public class ParceirosDto {
    
    @NotBlank
    @Size(min = 5 ,message = "Campo imagem não pode ser menor que 5 caracteres")
    @Size(max = 255 ,message = "Campo imagem não pode ser maior que 255 caracteres")
    private String imagem;

}

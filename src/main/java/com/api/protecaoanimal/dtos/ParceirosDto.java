package com.api.protecaoanimal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParceirosDto {
    
    @NotBlank
    private String imagem;

}

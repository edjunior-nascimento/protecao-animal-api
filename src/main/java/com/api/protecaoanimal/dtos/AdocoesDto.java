package com.api.protecaoanimal.dtos;

import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.TutoresModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "adocoes")
public class AdocoesDto {
    
    @NotBlank
    private TutoresModel tutores;
    
    @NotBlank
    private AnimaisModel animais;

}

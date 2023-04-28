package com.api.protecaoanimal.dtos;

import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.TutoresModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdocoesDto {
    
    @NotBlank
    private TutoresModel tutores;
    
    @NotBlank
    private AnimaisModel animais;

}

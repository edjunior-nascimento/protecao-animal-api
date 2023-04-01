package com.api.protecaoanimal.dtos;

import java.util.Date;

import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.TutoresModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "castracoes")
public class CastracoesDto {
    
    @NotBlank
    private TutoresModel tutores;
    
    @NotBlank
    private AnimaisModel animais;

    private Date agendamento;

}

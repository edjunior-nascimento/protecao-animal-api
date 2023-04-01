package com.api.protecaoanimal.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "animais")
public class AnimaisDto {

    @NotBlank(message = "Campo Nome obrigatório")
    private String nome;

    @Max(value = 1, message = "Campo especie não pode ser mais que 1 caracter")
    @Schema(description = "1-Canina, 2-Felino e 9-Outros")
    private int especie;

    @Max(value = 1, message = "Campo sexo não pode ser mais que 1 caracter")
    @Schema(description = "1-Macho, 2-Fêmea e 3-Indefinido")
    private int sexo;
    
    private Date nascimento;
    
    @Max(value = 1, message = "Campo porte não pode ser mais que 1 caracter")
    @Schema(description = "1-pequeno, 2-médio e 3-grande")
    private int porte;
    
    @Size(max = 50, message = "Campo raça não pode ser mais que 50 caracter")
    private String raca;
      
    private String historia;

    private String observacao;
    
    @Schema(description = "Ex: Ativo, Dócio, Apatico, Calmo e Hiperativo")
    private List<UUID>  temperamento;
    
    @Schema(description = "Ex: castrado, vermifugado e vacinado")
    private List<UUID> situacao;
  
    private List<FotosDto> fotos;

}

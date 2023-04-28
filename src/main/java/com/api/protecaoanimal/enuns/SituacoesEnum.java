package com.api.protecaoanimal.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacoesEnum {

    CASTRADO("Castrado", "O animal está castrado cirurgicamente."),
    DESPARASITADO("Desparasitado", "O animal não possue pulgas, carrapatos e piolhos, entre outros."),
    VACINADO("Vacinado", "O animal está atualizado em suas vacinas."),
    VERMIFUGADO("Vermifugado", "O animal não possue vermes intestinais.");
    

    private String nome;
    private String description;

}

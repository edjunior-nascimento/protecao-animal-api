package com.api.protecaoanimal.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegrasEnum {

    ADMIN("Administrador"),
    USER("Usuário"),
    GUEST("Convidado");

    private String description;

}

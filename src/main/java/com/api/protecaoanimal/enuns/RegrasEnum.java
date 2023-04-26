package com.api.protecaoanimal.enuns;

public enum RegrasEnum {

    ADMIN("Administrador", 0),
    USER("Usuário", 1),
    GUEST("Convidado", 2);

    private String description;
    private int code;

    RegrasEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}

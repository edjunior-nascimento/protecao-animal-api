package com.api.protecaoanimal.exceptions;

import org.springframework.security.access.AccessDeniedException;

public class AcessoNegadoException extends AccessDeniedException{

    public AcessoNegadoException(String msg) {
        super(msg);
    }
    
}

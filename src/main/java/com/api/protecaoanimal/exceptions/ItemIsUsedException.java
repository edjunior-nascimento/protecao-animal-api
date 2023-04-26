package com.api.protecaoanimal.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ItemIsUsedException extends DataIntegrityViolationException{

    public ItemIsUsedException(String msg) {
        super(msg);
    }
    
}

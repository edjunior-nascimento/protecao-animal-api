package com.api.protecaoanimal.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageStatus> itemNotFoundException(ItemNotFoundException it) {
        MessageStatus message = new MessageStatus(
            new Date(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            it.getMessage()
             );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    
}

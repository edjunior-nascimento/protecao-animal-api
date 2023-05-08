package com.api.protecaoanimal.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.api.protecaoanimal.enuns.StatusEnum;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageStatus> itemNotFoundException(ItemNotFoundException it) {
        MessageStatus message = new MessageStatus(
            new Date(),
            HttpStatus.NOT_FOUND.value(),
            StatusEnum.NOT_FOUND.getStatus(),
            it.getMessage().isEmpty()?StatusEnum.NOT_FOUND.getStatus():it.getMessage()
             );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemIsUsedException.class)
    public ResponseEntity<MessageStatus> itemIsUsedException(ItemIsUsedException it) {
        MessageStatus message = new MessageStatus(
            new Date(),
            HttpStatus.CONFLICT.value(),
            StatusEnum.CONFLICT.getStatus(),
            it.getMessage().isEmpty()?StatusEnum.CONFLICT.getStatus():it.getMessage()
             );
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageStatus> acessoNegadoException(AccessDeniedException it) {
        MessageStatus message = new MessageStatus(
            new Date(),
            HttpStatus.FORBIDDEN.value(),
            StatusEnum.FORBIDDEN.getStatus(),
            "Você não tem permissão para acessar este recurso"
             );
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
    
}

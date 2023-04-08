package com.api.protecaoanimal.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.protecaoanimal.enuns.StatusEnum;

@ControllerAdvice
public class ControlerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageStatus> itemNotFoundException(ItemNotFoundException it) {
        MessageStatus message = new MessageStatus(
            new Date(),
            StatusEnum.NOT_FOUND.getStatus(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            HttpStatus.NOT_FOUND.value()
             );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    
}

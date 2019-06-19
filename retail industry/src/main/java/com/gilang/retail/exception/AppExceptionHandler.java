package com.gilang.retail.exception;

import com.gilang.retail.dto.FailureResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.logging.Logger;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<FailureResponse> handleUserNotFound(DataNotFoundException ex){
        Logger.getLogger(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(FailureResponse.builder()
                        .message("The Data you sent is invalid / not match")
                        .timeStamp(new Date().getTime())
                        .error(new String[]{ex.getMessage()})
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailureResponse> handleArgumentInvalidxception(MethodArgumentNotValidException ex){
        Logger.getLogger(ex.getMessage());

        String[] errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f->f.getField() + " " + f.getDefaultMessage())
                .toArray(String[]::new);

        return ResponseEntity.badRequest()
                .body(FailureResponse.builder()
                        .message("Argument Not Valid")
                        .timeStamp(new Date().getTime())
                        .error(errors)
                        .build()
                );
    }

}

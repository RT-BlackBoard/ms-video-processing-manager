package com.rtblackboard.msvideoprocessingmanager.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404Error() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400Error(MethodArgumentNotValidException ex) {
        var erros  = ex.getFieldErrors();
        //return ResponseEntity.badRequest().body(ex.getFieldErrors());
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationErrorDTO::new).toList());
    }


    private record ValidationErrorDTO (String field, String  message){
        public ValidationErrorDTO(FieldError erro){

            this(erro.getField(),erro.getDefaultMessage());

        }


    }

}

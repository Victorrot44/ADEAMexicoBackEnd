/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adea.prueba.exceptions;

import java.util.HashMap;
import java.util.Map;
import javax.el.MethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

/**
 *
 * @author Victor
 */
@RestControllerAdvice
public class ErrorHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.put("status", 400);
        response.put("error_title", "Bad Requesr Error");
        response.put("message_error", errors);
        return response;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleInternalServerExceptions(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("error_title", "Internal Server Error");
        response.put("error_detail", exception.getClass().getCanonicalName());
        response.put("message_error", exception.getMessage());
        return response;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodNotFoundException.class)
    public Map<String, Object> handleNotFoundExceptions(MethodNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("error_title", "Not Found");
        response.put("message_error", exception.getMessage());
        return response;
    }
    
    /*
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(MethodNotAllowedException.class)
    public Map<String, Object> handleMethodNotAllowedExceptions(MethodNotAllowedException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", 405);
        response.put("error_title", "Method Not Allowed");
        response.put("message_error", exception.getMessage());
        return response;
    }
    */
    
}

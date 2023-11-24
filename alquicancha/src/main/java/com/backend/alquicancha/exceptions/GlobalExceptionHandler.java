package com.backend.alquicancha.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {//handles glocbal exceptions

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "Recurso no encontrado: " + exception.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            exceptionMessage.put(fieldName, errorMessage);
        });
        return exceptionMessage;
    }


    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequest(BadRequestException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("path", String.valueOf(exception.getClass()));
        exceptionMessage.put("message", "Recurso no encontrado: " + exception.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({JsonParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarJsonParseException(JsonParseException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("path", String.valueOf(exception.getClass()));
        exceptionMessage.put("message", exception.getMessage());
        return exceptionMessage;
    }

  /*  @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        FieldError fieldError = exception.getFi();
        if (fieldError != null) {
            String nombreCampo = fieldError.getField();
            exceptionMessage.put("name", nombreCampo);
            exceptionMessage.put("message", "Debe ingresar números en el campo " + nombreCampo);
        }
        return exceptionMessage;
    }*/


}

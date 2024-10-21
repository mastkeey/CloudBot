package ru.mastkey.cloudservice.exception.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;

import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        var errorResponse = ErrorResponse.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return "Поле " + fieldName + ": " + errorMessage;
                })
                .collect(Collectors.joining("; "));

        var errorResponse = ErrorResponse.builder()
                .status(ErrorType.BAD_REQUEST.getStatus())
                .code(ErrorType.BAD_REQUEST.getCode())
                .message(errorMessages)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

package ru.mastkey.cloudservice.exception.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;

import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        var errorResponse = ErrorResponse.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        for (Map.Entry<String, String> entry : ex.getHeaders().entrySet()) {
            headers.set(entry.getKey(), entry.getValue());
        }

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.valueOf(ex.getStatus()));
    }
}

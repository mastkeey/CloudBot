package ru.mastkey.cloudservice.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.mastkey.cloudservice.exception.ErrorType;
import ru.mastkey.cloudservice.exception.ServiceException;
import ru.mastkey.cloudservice.exception.response.ErrorResponse;

import java.util.stream.Collectors;

@UtilityClass
public class ResponseFactory {
    public ResponseEntity<ErrorResponse> createErrorResponseForMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
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

    public ResponseEntity<ErrorResponse> createErrorResponseForServiceException(ServiceException ex) {
        var errorResponse = ErrorResponse.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    }
}

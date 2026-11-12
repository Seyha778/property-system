package com.apextech.property.senghang.exception;

import com.apextech.property.senghang.common.ResponseMessage;
import com.apextech.property.senghang.common.ResponseMessageBuilder;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResponseMessage<Void>> handleNotFound(
//            ResourceNotFoundException ex) {
//
//        log.warn("Resource not found: {}", ex.getMessage());
//
//        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage<Object>> handleNotFound(
            ResourceNotFoundException ex) {

        log.error("Resource not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessageBuilder<>()
                        .error(ex.getMessage())
                        .devMessage("Exception error: " + ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage<Void>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");

        log.warn("Request validation failed: {}", message);

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage<Void>> handleConstraintViolation(
            ConstraintViolationException ex) {

        log.warn("Constraint violation: {}", ex.getMessage());

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMessage<Void>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        String message = "Invalid value for parameter: " + ex.getName();

        log.warn("Request parameter type mismatch: {}", message);

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage<Void>> handleGeneralException(
            Exception ex) {

        log.error("Unexpected application error", ex);

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error"
        );
    }

    private ResponseEntity<ResponseMessage<Void>> buildResponse(
            HttpStatus status,
            String message) {

        ResponseMessage<Void> response = ResponseMessage.<Void>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();

        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseMessage<Object>> handleBadRequest(
            BadRequestException ex) {

        return ResponseEntity.badRequest()
                .body(new ResponseMessageBuilder<>()
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseMessage<Object>> handleUnauthorized(
            UnauthorizedException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseMessageBuilder<>()
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMessage<Object>> handleConflict(
            ConflictException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseMessageBuilder<>()
                        .error(ex.getMessage())
                        .build());
    }

}
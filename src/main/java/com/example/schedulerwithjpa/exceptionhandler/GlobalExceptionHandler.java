package com.example.schedulerwithjpa.exceptionhandler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("handleResponseStatusException");

        response.put("ERROR", ex.getReason());
        response.put("STATUS",  ex.getStatusCode());
        response.put("TIMESTAMP", LocalDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException ex) {

        Map<String, Object> response = new HashMap<>();

        System.out.println("handleDataAccessException");

        response.put("TIMESTAMP", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("handleHandlerMethodValidationException");

        response.put("ERROR", ex.getAllErrors().get(0).getDefaultMessage());
        response.put("TIMESTAMP", LocalDateTime.now());
        response.put("STATUS", status);

        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> response = new HashMap<>();

        System.out.println("MethodArgumentNotValidException");

        response.put("ERROR", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.put("TIMESTAMP", LocalDateTime.now());
        response.put("STATUS", status);

        return ResponseEntity.status(status).body(response);
    }
}

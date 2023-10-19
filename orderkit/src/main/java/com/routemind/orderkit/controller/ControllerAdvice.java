package com.routemind.orderkit.controller;

import com.routemind.orderkit.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String PATH = "path";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        log.error("handlerEntityNotFoundException with message: {}", ex.getMessage(), ex);
        Map<String, Object> errorResponseBody = buildErrorResponseMap(request, ex.getMessage());
        return new ResponseEntity<>(errorResponseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handlerTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.error("handlerTypeMismatchException with message: {}", ex.getMessage(), ex);
        Map<String, Object> errorResponseBody = buildErrorResponseMap(request, "Bed request: argument type mismatch");
        return new ResponseEntity<>(errorResponseBody, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> buildErrorResponseMap(HttpServletRequest request, String message) {
        Map<String, Object> errorResponseMap = new LinkedHashMap<>();
        errorResponseMap.put(TIMESTAMP, LocalDateTime.now());
        errorResponseMap.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
        errorResponseMap.put(ERROR, "Bad Request");
        errorResponseMap.put(MESSAGE, message == null ? "no message" : message.toUpperCase());
        errorResponseMap.put(PATH, request.getServletPath());
        return errorResponseMap;
    }
}
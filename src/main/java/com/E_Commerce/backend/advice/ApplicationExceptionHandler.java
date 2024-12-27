package com.E_Commerce.backend.advice;

import com.E_Commerce.backend.lib.exception.InvalidEnumException;
import com.E_Commerce.backend.lib.exception.UserNotFoundException;
import com.E_Commerce.backend.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), "An unexpected error occurred"));
    }

    // Handle UserNotFoundException (custom exception, replace with your actual exception class)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "User Not Found exception"));
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ApiResponse> handleInvalidEnumException(InvalidEnumException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Invalid Enum Exception"));
    }

    // Handle UnauthorizedException (for handling authentication failures)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), "Unauthorized access"));
    }

    // Handle AccessDeniedException (for handling authorization failures)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(e.getMessage(), "Access denied"));
    }

    // Handle DataIntegrityViolationException (for JPA constraint violations)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Data integrity violation"));
    }

    // Handle IllegalArgumentException (e.g., for invalid input data)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Invalid argument"));
    }

    // Handle MethodArgumentNotValidException (for validation errors in request body)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Validation error", e.getBindingResult().getFieldErrors()));
    }

    // Handle NullPointerException (for handling null pointer exceptions)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), "Null Pointer Exception"));
    }
}

package com.E_Commerce.backend.advice;

import com.E_Commerce.backend.lib.exception.*;
import com.E_Commerce.backend.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        // Log the exception (optional)
        // logger.error("Unexpected error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unexpected error occurred", e.getMessage()));
    }

    // Handle UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User not found", e.getMessage()));
    }

    // Handle UserNotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Data not found", e.getMessage()));
    }

    // Handle UserAlreadyExistsException
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("User already exists", e.getMessage()));
    }

    // Handle UserAlreadyExistsException
    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<ApiResponse> handleAlreadyExists(AlreadyExists e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Already exists", e.getMessage()));
    }

    // Handle UserAlreadyExistsException
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid password", e.getMessage()));
    }

    // Handle UserAlreadyExistsException
    @ExceptionHandler(MissingFieldsException.class)
    public ResponseEntity<ApiResponse> handleMissingFieldsException(MissingFieldsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Some required fields are missing", e.getMessage()));
    }

    // Handle InvalidEnumException
    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ApiResponse> handleInvalidEnumException(InvalidEnumException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid enum value", e.getMessage()));
    }

    // Handle AuthenticationException (for authentication failures)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Authentication failed", e.getMessage()));
    }

    // Handle AccessDeniedException (for authorization failures)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("Access denied", e.getMessage()));
    }

    // Handle DataIntegrityViolationException (for database constraints violations)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Data integrity violation", e.getMessage()));
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid argument", e.getMessage()));
    }

    // Handle MethodArgumentNotValidException (validation errors in request body)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        // Collect validation errors
        String validationErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse("Validation error", validationErrors));
    }

    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Null Pointer Exception", e.getMessage()));
    }
}

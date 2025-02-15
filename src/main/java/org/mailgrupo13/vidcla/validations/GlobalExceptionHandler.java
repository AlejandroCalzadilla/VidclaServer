package org.mailgrupo13.vidcla.validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de validaciones (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    // Manejo de 404 (Not Found)
    @ExceptionHandler(ResourceNotFoundException.class) // Personaliza tu excepción
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    // Manejo de errores generales (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }




    // Manejo de 409 (Conflict) para recursos ya existentes
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> ExceptionConflictos(ResourceAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null),
                HttpStatus.CONFLICT
        );
    }




}

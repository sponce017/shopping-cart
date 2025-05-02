package com.prueba.sponce.exception;

import com.prueba.sponce.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(404).body(new ApiResponse<>("Order not found", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body(new ApiResponse<>("Unexpected error", ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(new ApiResponse<>("Product not found", ex.getMessage()));
    }
}

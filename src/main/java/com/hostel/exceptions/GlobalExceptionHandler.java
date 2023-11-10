package com.hostel.exceptions;

import com.hostel.payloads.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ApiResponse> handleResourceNotFound(Exception exception) {
        return new ResponseEntity<>(ApiResponse.builder().response(exception.getMessage()).isSuccess(true).httpStatusCode(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleFieldsError(MethodArgumentNotValidException exception) {
        List<String> fieldErrors = new ArrayList<>();
        exception.getAllErrors().forEach(error -> fieldErrors.add(error.getDefaultMessage()));
        Map<String, Object> result = new HashMap<>();
        result.put("errors", fieldErrors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }


}

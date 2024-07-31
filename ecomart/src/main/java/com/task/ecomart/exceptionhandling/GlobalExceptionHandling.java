package com.task.ecomart.exceptionhandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.task.ecomart.customexception.APIException;
import com.task.ecomart.customexception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String,String> errors = new HashMap<>();
        errors.put("timestamp", LocalDateTime.now().toString());
        errors.put("cause", ex.getMessage());
        errors.put("error", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<Map<String,String>> handleAPIException(APIException ex) {
		Map<String,String> errors = new HashMap<>();
		errors.put("timestamp", LocalDateTime.now().toString());
        errors.put("cause", ex.getMessage());
        errors.put("error", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
}

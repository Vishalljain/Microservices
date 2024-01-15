package com.authservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForbiddenRuntimeException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ForbiddenRuntimeException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
    
    
    @ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleOtherException(Exception ex) {
		String message = ex.getMessage();
		ApiResponse errors = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}

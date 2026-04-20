package com.ucb.kaffehaus.shared.presentation;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ucb.kaffehaus.shared.application.exception.ErrorResponse;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Error de validación",
				exception.getErrors());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}

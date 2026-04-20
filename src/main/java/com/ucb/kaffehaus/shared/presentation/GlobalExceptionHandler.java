package com.ucb.kaffehaus.shared.presentation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ucb.kaffehaus.shared.application.error.CustomException;
import com.ucb.kaffehaus.shared.application.exception.ValidationErrorResponse;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationException(ValidationException exception) {
		ValidationErrorResponse errorResponse = new ValidationErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Error de validacion",
				exception.getErrors());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ValidationErrorResponse> handleCustomException(CustomException exception) {
		HttpStatus httpStatus = HttpStatus.resolve(exception.getStatusCode());
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		Map<String, String> errors = new HashMap<>();
		errors.put("customException", exception.getMessage());

		ValidationErrorResponse errorResponse = new ValidationErrorResponse(
				LocalDateTime.now(),
				httpStatus.value(),
				"Error",
				errors);

		return ResponseEntity.status(httpStatus).body(errorResponse);
	}
}

package com.ucb.kaffehaus.shared.presentation;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ucb.kaffehaus.shared.application.error.CustomException;
import com.ucb.kaffehaus.shared.application.exception.ErrorResponse;
import com.ucb.kaffehaus.shared.application.exception.ValidationErrorResponse;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationException(ValidationException exception) {
		ValidationErrorResponse errorResponse = new ValidationErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Error al validar",
				exception.getErrors());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
		HttpStatus httpStatus = HttpStatus.resolve(exception.getStatusCode());
		if (httpStatus == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				httpStatus.value(),
				exception.getMessage()
        );

		return ResponseEntity.status(httpStatus).body(errorResponse);
	}
}

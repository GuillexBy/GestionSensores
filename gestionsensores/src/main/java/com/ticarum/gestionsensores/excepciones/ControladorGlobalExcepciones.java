package com.ticarum.gestionsensores.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorGlobalExcepciones {

	@ExceptionHandler(SensorNotFoundException.class)
	public ResponseEntity<String> handleSensorNotFoundException(SensorNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(HistorialNotFoundException.class)
	public ResponseEntity<String> handleHistorialNotFoundException(HistorialNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(GenericDatabaseException.class)
	public ResponseEntity<String> handleGenericDatabaseException(GenericDatabaseException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}

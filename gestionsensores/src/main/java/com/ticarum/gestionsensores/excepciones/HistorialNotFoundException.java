package com.ticarum.gestionsensores.excepciones;

public class HistorialNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public HistorialNotFoundException(Long sensorId) {
		super("El historial del sensor con id " + sensorId + " no tiene datos.");
	}
}

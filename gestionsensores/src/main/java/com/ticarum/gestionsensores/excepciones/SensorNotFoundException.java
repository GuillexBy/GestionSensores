package com.ticarum.gestionsensores.excepciones;

public class SensorNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SensorNotFoundException(Long sensorId) {
		super("El sensor con id " + sensorId + " no se encuentra en la base de datos jeje");
	}

}

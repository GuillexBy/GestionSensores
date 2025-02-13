package com.ticarum.gestionsensores.excepciones;

public class GenericDatabaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericDatabaseException() {
		super("Internal Database Error");
	}

}

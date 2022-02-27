package com.mec.mundoDisney.exception;

import java.text.MessageFormat;

public class PersonajeAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Pelicula already exist: {0}";

	public PersonajeAlreadyExistException(String name) {
		super(MessageFormat.format(MESSAGE, name));
	}
}

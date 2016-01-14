package com.example.esamesoftwareengeneering.exceptions;

public class InvalidOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String defaultMessage = "Invalid operation";
	
	
	public InvalidOperationException() {
        super(defaultMessage);
	}
	
	public InvalidOperationException(String message) {
        super(defaultMessage + ": " + message);
	}

}

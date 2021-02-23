package org.kastiks.exception;

public class InvalidOutputPathException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1538586534169381336L;

	public InvalidOutputPathException(String msg, Exception e) {
		super(msg, e);
	}

}

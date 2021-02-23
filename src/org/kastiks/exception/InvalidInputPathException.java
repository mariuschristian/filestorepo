package org.kastiks.exception;

public class InvalidInputPathException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6059538190962787721L;

	public InvalidInputPathException(String msg, Exception e) {
		super(msg,e);
	}

	public InvalidInputPathException(String msg) {
		super(msg);
	}

}

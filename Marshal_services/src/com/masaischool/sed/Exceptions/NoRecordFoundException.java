package com.masaischool.sed.Exceptions;

public class NoRecordFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRecordFoundException(String msssage) {
		super(msssage);
	}

	@Override
	public String toString() {
		return getMessage();
	}
	
	
}

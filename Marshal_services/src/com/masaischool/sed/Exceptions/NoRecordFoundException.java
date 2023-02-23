package com.masaischool.sed.Exceptions;

public class NoRecordFoundException extends Exception {
	public NoRecordFoundException(String msssage) {
		super(msssage);
	}

	@Override
	public String toString() {
		return getMessage();
	}
	
	
}

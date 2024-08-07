package com.employee.exception;

public class CsvProcessingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CsvProcessingException(String message) {
		super(message);
	}

}

package com.project.exception;

public class ErrorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2369561868972086516L;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ErrorException(String message){
		super(message);
		this.message = message;
		
	}
	
	
	
}

package com.nisum.portal.service.exception;

public class UserServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public UserServiceException(String errormessage) {
		super(errormessage);
		this.errorMessage = errormessage;
	}
	public UserServiceException() {
		super();
	}

}

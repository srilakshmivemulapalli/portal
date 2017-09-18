package com.nisum.portal.service.exception;

public class UsersServiceException  extends Exception{
	public UsersServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	private static final long serialVersionUID = 1L;
	private String errorMessage;

}

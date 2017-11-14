package com.nisum.portal.service.exception;

import com.nisum.portal.service.dto.Errors;

public class PageAccessServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public PageAccessServiceException(String errorMessage, Exception e) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		e.printStackTrace();
	}
	
	public PageAccessServiceException(String errorMessage, Errors e) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		e.getErrorMessage();
	}

	public PageAccessServiceException() {
		super();
	}

	public PageAccessServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
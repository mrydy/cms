package com.ydy.cms.model;

public class CMSException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CMSException() {
		super();
	}

	public CMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public CMSException(String message) {
		super(message);
	}

	public CMSException(Throwable cause) {
		super(cause);
	}
	
	
}

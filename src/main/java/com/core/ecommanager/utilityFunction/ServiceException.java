package com.core.ecommanager.utilityFunction;

public class ServiceException extends ApplicationException {
	static final long serialVersionUID = -1L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(Throwable causedBy) {
		super(causedBy);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable causedBy) {
		super(message, causedBy);
	}

}

/*
 * File: SystemException.java
 * Copyright 2009 cobornsdelivers Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * cobornsdelivers INC. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with cobornsdelivers Inc.
 *
 * Created on: May 22, 2009 / 3:45:26 PM
 * Created by: bnsgg1
 * Version: 1.0
 * Project Name: OnlineGrocery - SiteApp
 * Last updated / updated by: May 22, 2009 3:45:26 PM / bnsgg1
 */
package com.core.ecommanager.utilityFunction;

/**
 * 
 * @author bnsgg1
 *
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 6554854414648011566L;

	/**
	 * Default Constructor
	 */
	public SystemException() {
		super();
	}

	/**
	 * Constructor
	 * @param causedBy causedBy underlying exception
	 */
	public SystemException(final Throwable causedBy) {
		super(causedBy);
	}

	/**
	 * Constructor
	 * @param message String error message
	 */
	public SystemException(final String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param message String error message
	 * @param causedBy underlying exception
	 */
	public SystemException(final String message, final Throwable causedBy) {
		super(message, causedBy);
	}
}

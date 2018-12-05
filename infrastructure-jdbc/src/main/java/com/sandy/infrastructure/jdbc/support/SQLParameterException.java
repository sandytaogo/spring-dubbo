/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the BLK  License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.blk.com
 */
package com.sandy.infrastructure.jdbc.support;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * SQL Parameter Exception cause message
 * @description
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public class SQLParameterException extends RuntimeException {

	private static final long serialVersionUID = -6774593516326325999L;
	
	/**
	 * SQLParameterException
	 */
	public SQLParameterException() {
        super();
	}
	
	/**
	 * SQLParameterException
	 * @param message
	 */
	public SQLParameterException(String message) {
	    super(message);
	}
	
	/**
	 * SQLParameterException
	 * @param cause
	 */
	public SQLParameterException(Throwable cause) {
	    super(cause);
	}
	
	/**
	 * SQLParameterException
	 * @param message
	 * @param cause
	 */
	public SQLParameterException(String message, Throwable cause) {
	    super(message, cause);
	}
	
	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
	
	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}
	
	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}
	
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

}


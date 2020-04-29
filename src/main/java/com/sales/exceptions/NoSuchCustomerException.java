package com.sales.exceptions;

public class NoSuchCustomerException extends RuntimeException{

	/**
	 * Prompted to add this
	 */
	private static final long serialVersionUID = -5254503102529157534L;

	public NoSuchCustomerException(String msg) {
		super(msg);
	}
}

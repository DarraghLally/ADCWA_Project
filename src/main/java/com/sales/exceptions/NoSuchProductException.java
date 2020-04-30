package com.sales.exceptions;

public class NoSuchProductException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2174271586103776337L;
	
	public NoSuchProductException(String msg) {
		super(msg);
	}

}

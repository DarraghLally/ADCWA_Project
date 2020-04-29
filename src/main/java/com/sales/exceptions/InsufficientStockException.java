package com.sales.exceptions;

public class InsufficientStockException extends RuntimeException{

	/**
	 * Prompted to add this
	 */
	private static final long serialVersionUID = 4680452702022773600L;
	
	public InsufficientStockException(String msg) {
		super(msg);
	}

}

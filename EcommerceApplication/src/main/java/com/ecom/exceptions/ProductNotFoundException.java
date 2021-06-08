package com.ecom.exceptions;

public class ProductNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ProductNotFoundException(String status) {
		super(status);
	}

}

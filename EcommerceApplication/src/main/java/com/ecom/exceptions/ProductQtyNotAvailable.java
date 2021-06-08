package com.ecom.exceptions;

public class ProductQtyNotAvailable extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductQtyNotAvailable(String status) {
		super(status);
	}

}

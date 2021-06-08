package com.ecom.exceptions;

public class BalanceNotAvailableToBuyProduct extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BalanceNotAvailableToBuyProduct(String message) {
		super(message);
	}

}

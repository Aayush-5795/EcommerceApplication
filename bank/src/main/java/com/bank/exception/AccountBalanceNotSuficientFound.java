package com.bank.exception;

public class AccountBalanceNotSuficientFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountBalanceNotSuficientFound(String message) {
		super(message);
	}
	
}

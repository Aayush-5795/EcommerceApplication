package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStatus> handleUserNotFoundException(UserNotFound details) {
		ResponseStatus var = new ResponseStatus();
		var.setMessage(details.getLocalizedMessage());
		var.setStatusCode(901);
		return new ResponseEntity<>(var, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(AccountNotFound.class)
	public ResponseEntity<ResponseStatus> handleAccountNotFoundException(AccountNotFound details) {
		ResponseStatus var = new ResponseStatus();
		var.setMessage(details.getLocalizedMessage());
		var.setStatusCode(901);
		return new ResponseEntity<>(var, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(AccountBalanceNotSuficientFound.class)
	public ResponseEntity<ResponseStatus> handleAccountBalanceNotSuficientFoundException(AccountBalanceNotSuficientFound details) {
		ResponseStatus var = new ResponseStatus();
		var.setMessage(details.getLocalizedMessage());
		var.setStatusCode(901);
		return new ResponseEntity<>(var, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}

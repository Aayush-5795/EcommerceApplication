package com.ecom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStatus> handleProductNotFoundException(ProductNotFoundException details) {
		ResponseStatus errorStatus = new ResponseStatus();
		errorStatus.setMessage(details.getLocalizedMessage());
		errorStatus.setStatuscode(900);
		return new ResponseEntity<>(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStatus> handleUserNotFoundException(UserNotFound details) {
		ResponseStatus errorStatus = new ResponseStatus();
		errorStatus.setMessage(details.getLocalizedMessage());
		errorStatus.setStatuscode(901);
		return new ResponseEntity<>(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(ProductQtyNotAvailable.class)
	public ResponseEntity<ResponseStatus> handleProductQtyNotAvailableException(ProductQtyNotAvailable details) {
		ResponseStatus errorStatus = new ResponseStatus();
		errorStatus.setMessage(details.getLocalizedMessage());
		errorStatus.setStatuscode(905);
		return new ResponseEntity<>(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(BalanceNotAvailableToBuyProduct.class)
	public ResponseEntity<ResponseStatus> handleBalanceNotAvailableToBuyProductException(BalanceNotAvailableToBuyProduct details) {
		ResponseStatus errorStatus = new ResponseStatus();
		errorStatus.setMessage(details.getLocalizedMessage());
		errorStatus.setStatuscode(905);
		return new ResponseEntity<>(errorStatus, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}

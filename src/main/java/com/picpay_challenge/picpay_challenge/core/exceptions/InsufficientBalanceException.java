package com.picpay_challenge.picpay_challenge.core.exceptions;

public class InsufficientBalanceException extends RuntimeException {

	public InsufficientBalanceException() {
		super("Insufficient balance");
	}

}

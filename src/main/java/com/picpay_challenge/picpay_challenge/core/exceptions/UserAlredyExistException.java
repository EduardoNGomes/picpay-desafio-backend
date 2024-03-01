package com.picpay_challenge.picpay_challenge.core.exceptions;

public class UserAlredyExistException extends RuntimeException {

	public UserAlredyExistException() {

		super("User alreay exist");
	}

}

package com.picpay_challenge.picpay_challenge.core.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException() {

		super("User already exist");
	}

}

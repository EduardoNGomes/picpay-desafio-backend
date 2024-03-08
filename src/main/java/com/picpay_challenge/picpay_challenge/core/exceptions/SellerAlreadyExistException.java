package com.picpay_challenge.picpay_challenge.core.exceptions;

public class SellerAlreadyExistException extends RuntimeException {
	public SellerAlreadyExistException() {
		super("Seller already exist");
	}

}

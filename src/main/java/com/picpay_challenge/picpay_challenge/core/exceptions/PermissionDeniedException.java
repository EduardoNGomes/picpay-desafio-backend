package com.picpay_challenge.picpay_challenge.core.exceptions;

public class PermissionDeniedException extends RuntimeException {

	public PermissionDeniedException(String customizeMsg) {
		super("Permission denied to do this action. " + customizeMsg);
	}

}

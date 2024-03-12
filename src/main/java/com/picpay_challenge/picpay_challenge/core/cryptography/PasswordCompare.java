package com.picpay_challenge.picpay_challenge.core.cryptography;

public interface PasswordCompare {

	boolean compare(String hash, String plain);

}

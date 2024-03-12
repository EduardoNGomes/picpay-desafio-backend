package com.picpay_challenge.picpay_challenge.test.cryptography;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordCompare;
import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;

public class MockPasswordEncoder implements PasswordEncoder, PasswordCompare {

	@Override
	public String encoder(String plain) {

		return plain + "-hashed";
	}

	@Override
	public boolean compare(String hash, String plain) {
		return hash.equals(plain + "hashed");

	}

}

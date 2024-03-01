package com.picpay_challenge.picpay_challenge.test.cryptography;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MockPasswordEncoder implements PasswordEncoder {

	@Override
	public String encoder(String plain) {

		return plain + "-hashed";
	}

}

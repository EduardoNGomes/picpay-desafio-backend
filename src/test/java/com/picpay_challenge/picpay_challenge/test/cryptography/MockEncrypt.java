package com.picpay_challenge.picpay_challenge.test.cryptography;

import com.picpay_challenge.picpay_challenge.core.cryptography.Encrypt;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;

public class MockEncrypt implements Encrypt {


	@Override
	public String create(String id, Roles role) {
		return id + role;
	}

}

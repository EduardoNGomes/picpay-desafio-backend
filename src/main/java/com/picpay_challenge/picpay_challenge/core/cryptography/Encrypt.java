package com.picpay_challenge.picpay_challenge.core.cryptography;

import com.picpay_challenge.picpay_challenge.domain.enums.Roles;

public interface Encrypt {

	String create(String id, Roles role);

}

package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.domain.entities.Account;

public interface UserAccountRepository {

	Account create(Account account);

	void increaseBalance(Double value);

	void decreaseBalance(Double value);

}

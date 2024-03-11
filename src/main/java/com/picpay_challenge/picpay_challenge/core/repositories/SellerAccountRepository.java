package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;

public interface SellerAccountRepository {

	Account findByOwnerId(UniqueEntityID ownerId);

	Account findById(UniqueEntityID ownerId);

	void create(Account account);

	void increaseBalance(Double value);

}

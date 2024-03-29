package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Account;
import java.util.Optional;

public interface AccountRepository {

	Optional<Account> findByOwnerId(UniqueEntityID ownerId);

	Optional<Account> findById(UniqueEntityID id);

	void create(Account account);

	void increaseBalance(Double value, UniqueEntityID id);

	void decreaseBalance(Double value, UniqueEntityID Id);

}

package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class InMemoryAccountRepository implements AccountRepository {

	List<Account> items = new ArrayList<>();

	@Override
	public Optional<Account> findByOwnerId(UniqueEntityID ownerId) {
		return Optional.empty();
	}

	@Override
	public Optional<Account> findById(UniqueEntityID ownerId) {
		return Optional.empty();
	}

	@Override
	public void create(Account account) {
		this.items.add(account);
	}

	@Override
	public void increaseBalance(Double value, UniqueEntityID id) {

	}

	@Override
	public void decreaseBalance(Double value, UniqueEntityID id) {

	}

}

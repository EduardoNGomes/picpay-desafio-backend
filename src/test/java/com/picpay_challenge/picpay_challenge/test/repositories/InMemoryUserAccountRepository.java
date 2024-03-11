package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.UserAccountRepository;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class InMemoryUserAccountRepository implements UserAccountRepository {

	List<Account> items = new ArrayList<>();

	@Override
	public Account findByOwnerId(UniqueEntityID ownerId) {
		return null;
	}

	@Override
	public Account findById(UniqueEntityID ownerId) {
		return null;
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

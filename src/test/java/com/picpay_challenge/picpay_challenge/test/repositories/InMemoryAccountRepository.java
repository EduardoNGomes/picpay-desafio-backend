package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InMemoryAccountRepository implements AccountRepository {

	List<Account> items = new ArrayList<>();

	@Override
	public Optional<Account> findByOwnerId(UniqueEntityID ownerId) {

		return this.items.stream()
				.filter(account -> ownerId.equals(new UniqueEntityID(Optional.ofNullable(account.getOwnerIdValue()))))
				.findFirst();


	}

	@Override
	public Optional<Account> findById(UniqueEntityID id) {
		return this.items.stream()
				.filter(account -> id.equals(new UniqueEntityID(Optional.ofNullable(account.getIdValue()))))
				.findFirst();
	}

	@Override
	public void create(Account account) {
		this.items.add(account);
	}

	@Override
	public void increaseBalance(Double value, UniqueEntityID id) {
		this.items = this.items.stream().map(account -> {
			if (id.equals(new UniqueEntityID(Optional.ofNullable(account.getIdValue())))) {
				var props = IAccount.builder()
						.ownerID(new UniqueEntityID(Optional.ofNullable(account.getOwnerIdValue())))
						.balance(account.getBalance() + value).build();

				return Account.create(props, Optional.of(id), account.getRole());
			} else {
				return account;
			}

		}).toList();

	}

	@Override
	public void decreaseBalance(Double value, UniqueEntityID id) {

		this.items = this.items.stream().map(account -> {
			if (id.equals(new UniqueEntityID(Optional.ofNullable(account.getIdValue())))) {
				var props = IAccount.builder()
						.ownerID(new UniqueEntityID(Optional.ofNullable(account.getOwnerIdValue())))
						.balance(account.getBalance() - value).build();

				return Account.create(props, Optional.of(id), account.getRole());
			} else {
				return account;
			}

		}).toList();

	}

}

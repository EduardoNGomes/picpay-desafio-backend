package com.picpay_challenge.picpay_challenge.factory;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import java.util.Optional;
import java.util.UUID;

public class AccountFactory {

	public static Account CreateAccount(Optional<UUID> idToCreate, Optional<Double> balanceToCreate, Optional<UUID> ownerIdToCreate) {

		UniqueEntityID id;

		if (idToCreate.isPresent()) {
			id = new UniqueEntityID(idToCreate);

		} else {
			id = new UniqueEntityID(Optional.empty());
		}

		Double balance;

		balance = balanceToCreate.orElse(0.0);

		UniqueEntityID ownerId;

		if (ownerIdToCreate.isPresent()) {
			ownerId = new UniqueEntityID(ownerIdToCreate);

		} else {
			ownerId = new UniqueEntityID(Optional.empty());
		}

		var props = IAccount.builder().balance(balance).ownerID(ownerId)
				.build();

		return Account.create(props, Optional.of(id), Roles.USER);
	}

}

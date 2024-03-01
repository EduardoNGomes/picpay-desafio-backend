package com.picpay_challenge.picpay_challenge.domain.entities;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;

import java.util.Optional;
import java.util.UUID;

public class Account extends Entity<IAccount> {

	protected Account(IAccount props, Optional<UniqueEntityID> id) {

		super(props, id);
	}

	static Account create(IAccount props, Optional<UniqueEntityID> id) {

		return new Account(props, id);

	}

	public Double getBalance() {

		return this.props.balance;
	}

	public UUID geOwnerIdValue() {

		return this.props.ownerID.toValue();
	}

	public String getOwnerIdString() {

		return this.props.ownerID.toString();
	}

}

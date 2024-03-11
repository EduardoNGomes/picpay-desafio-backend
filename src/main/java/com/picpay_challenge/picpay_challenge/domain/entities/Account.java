package com.picpay_challenge.picpay_challenge.domain.entities;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("LombokGetterMayBeUsed")
public class Account extends Entity<IAccount> {

	private final Roles role;


	protected Account(IAccount props, Optional<UniqueEntityID> id, Roles role) {

		super(props, id);
		this.role = role;
	}

	public static Account create(IAccount props, Optional<UniqueEntityID> id, Roles role) {

		return new Account(props, id, role);

	}

	public Roles getRole() {
		return this.role;
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

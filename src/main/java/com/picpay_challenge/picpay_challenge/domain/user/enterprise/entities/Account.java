package com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IAccount;
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

	public UUID getOwnerIdValue() {

		return this.props.ownerID.toValue();
	}

	public String getOwnerIdString() {

		return this.props.ownerID.toString();
	}

}

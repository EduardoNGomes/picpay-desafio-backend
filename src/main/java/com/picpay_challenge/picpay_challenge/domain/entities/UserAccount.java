package com.picpay_challenge.picpay_challenge.domain.entities;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserAccount;
import java.util.Optional;

public class UserAccount extends Account implements IUserAccount {
	private final Roles role;


	protected UserAccount(IAccount props, Optional<UniqueEntityID> id) {
		super(props, id);
		this.role = Roles.USER;

	}

	public Roles getRole() {
		return this.role;
	}

	@Override
	public void increaseBalance(Double value) {
		this.props.balance += value;
	}

	@Override
	public void decreaseBalance(Double value) {
		this.props.balance += value;
	}

}

package com.picpay_challenge.picpay_challenge.domain.entities;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.interfaces.ISellerAccount;
import java.util.Optional;

public class SellerAccount extends Account implements ISellerAccount {
	private final Roles role;

	protected SellerAccount(IAccount props, Optional<UniqueEntityID> id) {
		super(props, id);
		this.role = Roles.SELLER;

	}

	public Roles getRole() {
		return this.role;
	}

	@Override
	public void increaseBalance(Double value) {
		this.props.balance += value;
	}

}

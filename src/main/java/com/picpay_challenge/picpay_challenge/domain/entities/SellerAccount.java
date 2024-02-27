package com.picpay_challenge.picpay_challenge.domain.entities;

import java.util.Optional;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.interfaces.ISellerAccount;

public class SellerAccount extends Account implements ISellerAccount {
  private Roles role;

  protected SellerAccount(IAccount props, Optional<UniqueEntityID> id) {
    super(props, id);
    this.role = Roles.SELLER;

  }

  public Roles getRole() {
    return role;
  }

  @Override
  public void increaseBalance(Double value) {
    this.props.balance += value;
  }

}

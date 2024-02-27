package com.picpay_challenge.picpay_challenge.domain.entities;

import java.util.Optional;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserAccount;

public class UserAccount extends Account implements IUserAccount {

  protected UserAccount(IAccount props, Optional<UniqueEntityID> id) {
    super(props, id);
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

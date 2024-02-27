package com.picpay_challenge.picpay_challenge.domain.entities;

import java.util.Optional;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;

public class Account extends Entity<IAccount> {

  protected Account(IAccount props, Optional<UniqueEntityID> id) {
    super(props, id);
  }

  public void increaseBalance(Double value) {
    this.props.balance += value;
  }

  public void decreaseBalance(Double value) {
    this.props.balance += value;
  }

  public Double getBalance() {
    return this.props.balance;
  }

  static Account create(IAccount props, Optional<UniqueEntityID> id) {
    var account = new Account(props, id);

    return account;
  }
}

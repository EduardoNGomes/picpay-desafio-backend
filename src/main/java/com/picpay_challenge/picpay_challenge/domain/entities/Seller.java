package com.picpay_challenge.picpay_challenge.domain.entities;

import java.util.Optional;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.vo.UniqueCNPJ;

public class Seller extends Entity<IUserSeller> {
  private UniqueCNPJ cnpj;

  protected Seller(IUserSeller props, Optional<UniqueEntityID> id) {
    super(props, id);
  }

  public String getCnpj() {
    return this.cnpj.getValue();
  }

  public String getFirstName() {
    return this.props.firstName;
  }

  public String getLastName() {
    return this.props.lastName;
  }

  public String getEmail() {
    return this.props.email.getValue();
  }

  public String getPassword() {
    return this.props.password;
  }

  static Seller create(IUserSeller props, Optional<UniqueEntityID> id) {
    var user = new Seller(props, id);

    return user;
  }
}

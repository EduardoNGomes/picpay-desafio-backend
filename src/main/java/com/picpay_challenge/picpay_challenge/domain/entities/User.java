package com.picpay_challenge.picpay_challenge.domain.entities;

import java.util.Optional;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.vo.UniqueCPF;

public class User extends Entity<IUserSeller> {
  private UniqueCPF cpf;

  protected User(IUserSeller props, Optional<UniqueEntityID> id) {
    super(props, id);
  }

  public String getCpf() {
    return this.cpf.getValue();
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

  static User create(IUserSeller props, Optional<UniqueEntityID> id) {
    var user = new User(props, id);

    return user;
  }

}

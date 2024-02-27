package com.picpay_challenge.picpay_challenge.core.entity;

import java.util.Optional;
import java.util.UUID;

import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends UniqueId {
  private String name;
  private UniqueEmail email;
  private String lastName;
  private String password;

  public User(Optional<UUID> id) {
    super(id);
  }

}

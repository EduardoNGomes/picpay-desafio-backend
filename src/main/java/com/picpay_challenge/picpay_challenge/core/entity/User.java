package com.picpay_challenge.picpay_challenge.core.entity;

import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;

import lombok.Data;

@Data
public class User {
  private String name;
  private UniqueEmail email;
  private String lastName;
  private String password;

}

package com.picpay_challenge.picpay_challenge.domain.enums;

public enum Roles {
  USER("USER"),
  SELLER("SELLER");

  private String state;

  Roles(String state) {
    this.state = state;
  }

  public String getState() {
    return this.state;
  }
}

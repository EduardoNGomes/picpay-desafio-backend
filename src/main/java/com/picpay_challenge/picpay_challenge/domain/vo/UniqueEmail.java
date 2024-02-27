package com.picpay_challenge.picpay_challenge.domain.vo;

public class UniqueEmail {
  private String email;

  public UniqueEmail(String email) {
    this.email = email;

  }

  public String getValue() {
    return this.email;
  }

  public boolean equals(UniqueEmail email) {
    return email.toString() == this.email;
  }

}

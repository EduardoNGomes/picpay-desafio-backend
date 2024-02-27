package com.picpay_challenge.picpay_challenge.core.vo;

import java.util.Optional;
import java.util.UUID;

public class UniqueCPF {
  private String cpf;

  public UniqueCPF(Optional<String> cpf) {
    if (cpf.isPresent()) {
      this.cpf = cpf.get();
    } else {
      this.cpf = UUID.randomUUID().toString();
    }
  }

  public String getValue() {
    return this.cpf;
  }

}

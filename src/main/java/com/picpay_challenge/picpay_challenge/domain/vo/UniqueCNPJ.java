package com.picpay_challenge.picpay_challenge.domain.vo;

import java.util.Optional;
import java.util.UUID;

public class UniqueCNPJ {
  private String cnpj;

  public UniqueCNPJ(Optional<String> cnpj) {
    if (cnpj.isPresent()) {
      this.cnpj = cnpj.get();
    } else {
      this.cnpj = UUID.randomUUID().toString();
    }
  }

  public String getValue() {
    return this.cnpj;
  }

  public boolean equals(UniqueCNPJ cnpj) {
    return cnpj.toString() == this.cnpj;
  }

}

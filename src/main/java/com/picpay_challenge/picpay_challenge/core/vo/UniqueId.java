package com.picpay_challenge.picpay_challenge.core.vo;

import java.util.Optional;
import java.util.UUID;

public class UniqueId {
  private UUID id;

  public UniqueId(Optional<UUID> id) {
    if (id.isPresent()) {
      this.id = id.get();
    } else {
      this.id = UUID.randomUUID();
    }

  }

  public UUID toValue() {
    return this.id;
  }

  public String toString() {
    return this.id.toString();
  }
}

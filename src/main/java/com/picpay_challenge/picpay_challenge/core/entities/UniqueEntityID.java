package com.picpay_challenge.picpay_challenge.core.entities;

import java.util.Optional;
import java.util.UUID;

public class UniqueEntityID {
  private UUID value;

  public UniqueEntityID(Optional<UUID> value) {
    if (value.isPresent()) {
      this.value = value.get();
    } else {
      this.value = UUID.randomUUID();
    }

  }

  public UUID toValue() {
    return this.value;
  }

  public String toString() {
    return this.value.toString();
  }

  public boolean equals(UniqueEntityID id) {
    return id.toValue() == this.value;
  }
}

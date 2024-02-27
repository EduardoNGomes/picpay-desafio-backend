package com.picpay_challenge.picpay_challenge.core.entities;

import java.util.Optional;
import java.util.UUID;

public abstract class Entity<Props> {

  private UniqueEntityID id;

  public UUID getIdValue() {
    return this.id.toValue();
  }

  public String getIdString() {
    return this.id.toString();
  }

  protected Props props;

  protected Entity(Props props, Optional<UniqueEntityID> id) {
    this.props = props;

    if (id.isEmpty()) {
      this.id = new UniqueEntityID(null);
    } else {
      this.id = id.get();
    }
  }
}

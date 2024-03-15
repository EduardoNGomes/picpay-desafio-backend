package com.picpay_challenge.picpay_challenge.core.entities;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalIsPresent")
public class UniqueEntityID {

	private final UUID value;

	public UniqueEntityID(Optional<UUID> value) {

		if (value.isEmpty()) {
			this.value = UUID.randomUUID();
		} else {
			this.value = value.get();
		}

	}

	public UUID toValue() {

		return this.value;
	}

	public String toString() {

		return this.value.toString();
	}

	public boolean equals(UniqueEntityID id) {

		return id.toString().equals(this.toString());
	}

}

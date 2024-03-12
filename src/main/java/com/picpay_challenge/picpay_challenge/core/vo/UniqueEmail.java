package com.picpay_challenge.picpay_challenge.core.vo;

public class UniqueEmail {

	private final String email;

	public UniqueEmail(String email) {

		this.email = email;

	}

	public String getValue() {

		return this.email;
	}

	public boolean equals(UniqueEmail email) {

		return email.getValue().equals(this.email);
	}

}

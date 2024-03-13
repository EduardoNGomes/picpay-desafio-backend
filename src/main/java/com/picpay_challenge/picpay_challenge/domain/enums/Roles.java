package com.picpay_challenge.picpay_challenge.domain.enums;

import lombok.Getter;

@Getter
public enum Roles {
	USER("USER"), SELLER("SELLER");

	private final String state;

	Roles(String state) {
		this.state = state;
	}

}

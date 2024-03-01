package com.picpay_challenge.picpay_challenge.core.vo;

import java.util.UUID;

public class UniqueCPF {

	private final String cpf;

	public UniqueCPF(String cpf) {

		if (cpf.isEmpty()) {
			this.cpf = cpf;
		} else {
			this.cpf = UUID.randomUUID().toString();
		}
	}

	public String getValue() {

		return this.cpf;
	}

	public boolean equals(UniqueCPF cpf) {

		return cpf.toString().equals(this.cpf);
	}

}

package com.picpay_challenge.picpay_challenge.core.vo;

import java.util.UUID;

public class UniqueCNPJ {

	private final String cnpj;

	public UniqueCNPJ(String cnpj) {

		if (cnpj.isEmpty()) {
			this.cnpj = cnpj;
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

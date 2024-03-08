package com.picpay_challenge.picpay_challenge.domain.entities;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import java.util.Optional;

public class Seller extends Entity<IUserSeller> {

	private final UniqueCNPJ cnpj;

	protected Seller(UniqueCNPJ cnpj, IUserSeller props, Optional<UniqueEntityID> id) {

		super(props, id);
		this.cnpj = cnpj;
	}

	public static Seller create(UniqueCNPJ cnpj, IUserSeller props, Optional<UniqueEntityID> id) {

		return new Seller(cnpj, props, id);

	}

	public String getCnpj() {

		return this.cnpj.getValue();
	}

	public String getFirstName() {

		return this.props.firstName;
	}

	public String getLastName() {

		return this.props.lastName;
	}

	public String getEmail() {

		return this.props.email.getValue();
	}

	public String getPassword() {

		return this.props.password;
	}

}

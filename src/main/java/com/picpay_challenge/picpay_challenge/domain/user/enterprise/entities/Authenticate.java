package com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities;

import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Authenticate {

	private final UniqueEmail email;
	private final String password;
	private final Roles role;
	private final String id;

	public Authenticate(UniqueEmail email, String password, Roles role, String id) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.id = id;
	}


}

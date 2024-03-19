package com.picpay_challenge.picpay_challenge.factory;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.User;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.user.application.User.dto.CreateUserUseCaseDTO;
import java.util.Optional;
import java.util.UUID;

public class UserFactory {

	public static User CreateUser(Optional<UUID> idToCreate) {

		UniqueEntityID id;

		if (idToCreate.isPresent()) {
			id = new UniqueEntityID(idToCreate);

		} else {
			id = new UniqueEntityID(Optional.empty());
		}

		var dto = CreateUserUseCaseDTO.builder().cpf("12345678")
				.email("johnDoe@hotmail.com").firstName("John").lastName("Doe")
				.password("123456").build();

		var email = new UniqueEmail("johnDoe@hotmail.com");
		var cpf = new UniqueCPF("123");

		var props = IUserSeller.builder().email(email)
				.firstName(dto.getFirstName()).lastName(dto.getLastName())
				.password(dto.getPassword()).build();

		return User.create(cpf, props, Optional.of(id));
	}

}

package com.picpay_challenge.picpay_challenge.factory;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Seller;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.useCases.Seller.dto.CreateSellerUseCaseDTO;
import java.util.Optional;
import java.util.UUID;

public class SellerFactory {

	public static Seller CreateSeller(Optional<UUID> idToCreate) {

		UniqueEntityID id;

		if (idToCreate.isPresent()) {
			id = new UniqueEntityID(idToCreate);

		} else {
			id = new UniqueEntityID(Optional.empty());
		}

		var dto = CreateSellerUseCaseDTO.builder().cnpj("12345678")
				.email("johnDoe@hotmail.com").firstName("John").lastName("Doe")
				.password("123456").build();

		var email = new UniqueEmail("johnDoe@hotmail.com");
		var cnpj = new UniqueCNPJ("123");

		var props = IUserSeller.builder().email(email)
				.firstName(dto.getFirstName()).lastName(dto.getLastName())
				.password(dto.getPassword()).build();

		return Seller.create(cnpj, props, Optional.of(id));
	}

}

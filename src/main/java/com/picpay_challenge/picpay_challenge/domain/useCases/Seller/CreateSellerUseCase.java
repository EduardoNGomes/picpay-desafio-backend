package com.picpay_challenge.picpay_challenge.domain.useCases.Seller;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.exceptions.SellerAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.SellerRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Seller;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.useCases.Seller.dto.CreateSellerUseCaseDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateSellerUseCase {
	private final SellerRepository sellerRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CreateSellerUseCase(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;

		this.sellerRepository = sellerRepository;
	}

	public String execute(CreateSellerUseCaseDTO dto) throws
			SellerAlreadyExistException {

		var cnpj = new UniqueCNPJ(dto.getCnpj());
		var email = new UniqueEmail(dto.getEmail());

		var userExist = sellerRepository.findByEmailOrCnpj(email, cnpj);

		if (userExist.isPresent()) {
			throw new SellerAlreadyExistException();
		}

		dto.setPassword(passwordEncoder.encoder(dto.getPassword()));

		var props = IUserSeller.builder().email(email)
				.firstName(dto.getFirstName()).lastName(dto.getLastName())
				.password(dto.getPassword()).build();

		var sellerToCreate = Seller.create(cnpj, props, Optional.empty());

		sellerRepository.create(sellerToCreate);

		return "created";
	}

}

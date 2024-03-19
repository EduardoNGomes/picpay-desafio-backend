package com.picpay_challenge.picpay_challenge.domain.user.application.Seller;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.exceptions.SellerAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.core.repositories.SellerRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Seller;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.user.application.Seller.dto.CreateSellerUseCaseDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateSellerUseCase {
	private final SellerRepository sellerRepository;

	private final PasswordEncoder passwordEncoder;

	private final AccountRepository accountRepository;

	@Autowired
	public CreateSellerUseCase(SellerRepository sellerRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {

		this.passwordEncoder = passwordEncoder;

		this.sellerRepository = sellerRepository;

		this.accountRepository = accountRepository;


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

		var id = new UniqueEntityID(Optional.ofNullable(sellerToCreate.getIdValue()));

		var accountProps = IAccount.builder().balance(0.0).ownerID(id).build();

		var accountId = Optional.of(new UniqueEntityID(Optional.of(UUID.randomUUID())));

		var account = Account.create(accountProps, accountId, Roles.SELLER);

		accountRepository.create(account);

		return "created";
	}

}

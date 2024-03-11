package com.picpay_challenge.picpay_challenge.domain.useCases.User;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.UserAccountRepository;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.entities.User;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.dto.CreateUserUseCaseDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;


	private final UserAccountRepository userAccountRepository;

	@Autowired
	public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {

		this.passwordEncoder = passwordEncoder;

		this.userRepository = userRepository;

		this.userAccountRepository = userAccountRepository;

	}

	public String execute(CreateUserUseCaseDTO dto) throws
			UserAlreadyExistException {

		var cpf = new UniqueCPF(dto.getCpf());
		var email = new UniqueEmail(dto.getEmail());

		var userExist = userRepository.findByEmailOrCpf(email, cpf);

		if (userExist.isPresent()) {
			throw new UserAlreadyExistException();
		}

		dto.setPassword(passwordEncoder.encoder(dto.getPassword()));

		var props = IUserSeller.builder().email(email)
				.firstName(dto.getFirstName()).lastName(dto.getLastName())
				.password(dto.getPassword()).build();

		var userToCreate = User.create(cpf, props, Optional.empty());

		userRepository.create(userToCreate);

		var id = new UniqueEntityID(Optional.ofNullable(userToCreate.getIdValue()));

		var accountProps = IAccount.builder().balance(0.0).ownerID(id).build();

		var accountId = Optional.of(new UniqueEntityID(Optional.of(UUID.randomUUID())));

		var account = Account.create(accountProps, accountId, Roles.USER);

		userAccountRepository.create(account);

		return "created";
	}

}

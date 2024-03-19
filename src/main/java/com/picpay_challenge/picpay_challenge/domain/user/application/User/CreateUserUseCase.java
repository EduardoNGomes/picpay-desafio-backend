package com.picpay_challenge.picpay_challenge.domain.user.application.User;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.User;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IAccount;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.user.application.User.dto.CreateUserUseCaseDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;


	private final AccountRepository accountRepository;

	@Autowired
	public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {

		this.passwordEncoder = passwordEncoder;

		this.userRepository = userRepository;

		this.accountRepository = accountRepository;

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

		accountRepository.create(account);

		return "created";
	}

}

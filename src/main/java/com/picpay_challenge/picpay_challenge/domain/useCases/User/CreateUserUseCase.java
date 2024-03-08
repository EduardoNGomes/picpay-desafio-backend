package com.picpay_challenge.picpay_challenge.domain.useCases.User;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.User;
import com.picpay_challenge.picpay_challenge.domain.interfaces.IUserSeller;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.dto.CreateUserUseCaseDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;

		this.userRepository = userRepository;
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

		return "created";
	}

}

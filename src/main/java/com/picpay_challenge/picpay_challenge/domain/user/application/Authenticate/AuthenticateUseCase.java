package com.picpay_challenge.picpay_challenge.domain.user.application.Authenticate;

import com.picpay_challenge.picpay_challenge.core.cryptography.Encrypt;
import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordCompare;
import com.picpay_challenge.picpay_challenge.core.exceptions.CredentialsInvalidException;
import com.picpay_challenge.picpay_challenge.core.repositories.AuthenticateRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUseCase {

	private final AuthenticateRepository authenticateRepository;
	private final Encrypt encrypt;
	private final PasswordCompare passwordCompare;

	@Autowired
	public AuthenticateUseCase(AuthenticateRepository authenticateRepository, PasswordCompare passwordCompare, Encrypt encrypt) {
		this.authenticateRepository = authenticateRepository;
		this.passwordCompare = passwordCompare;
		this.encrypt = encrypt;
	}


	public String execute(String email, String password) throws
			CredentialsInvalidException {

		var emailToFind = new UniqueEmail(email);

		var user = authenticateRepository.findByEmail(emailToFind);

		if (user.isEmpty()) {
			throw new CredentialsInvalidException();
		}

		var validPassword = passwordCompare.compare(user.get()
				.getPassword(), password);

		if (!validPassword) {
			throw new CredentialsInvalidException();
		}

		String token;

		token = encrypt.create(user.get().getId(), user.get().getRole());

		return token;

	}

}

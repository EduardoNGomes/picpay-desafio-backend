package com.picpay_challenge.picpay_challenge.useCase.Authenticate;


import com.picpay_challenge.picpay_challenge.core.exceptions.CredentialsInvalidException;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Authenticate;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.user.application.Authenticate.AuthenticateUseCase;
import com.picpay_challenge.picpay_challenge.test.cryptography.MockEncrypt;
import com.picpay_challenge.picpay_challenge.test.cryptography.MockPassword;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemoryAuthenticateRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthenticateTest {
	private AuthenticateUseCase useCase;
	private InMemoryAuthenticateRepository authenticateRepository;
	private Authenticate mockUser;

	@BeforeEach
	public void setup() {

		MockEncrypt encrypt = new MockEncrypt();
		this.authenticateRepository = new InMemoryAuthenticateRepository();
		MockPassword passwordCompare = new MockPassword();

		this.mockUser = new Authenticate(new UniqueEmail("jonhDoe@email.com"), passwordCompare.encoder("123456"), Roles.USER, UUID.randomUUID()
				.toString());

		this.useCase = new AuthenticateUseCase(authenticateRepository, passwordCompare, encrypt);


	}

	@Test
	@DisplayName("Should not be able to create token if email is invalid")
	public void should_not_be_able_to_create_token_if_email_is_invalid() {

		final CredentialsInvalidException e = assertThrows(CredentialsInvalidException.class, () -> this.useCase.execute("Doe@email.com", "123456"));

		assertThat(e).isNotNull();
	}

	@Test
	@DisplayName("Should not be able to create token if password is invalid")
	public void should_not_be_able_to_create_token_if_password_is_invalid() {
		this.authenticateRepository.create(mockUser);

		final CredentialsInvalidException e = assertThrows(CredentialsInvalidException.class, () -> this.useCase.execute(mockUser.getEmail()
				.toString(), mockUser.getPassword()));

		assertThat(e).isNotNull();

	}

	@Test
	@DisplayName("Should  be able to create token ")
	public void should_be_able_to_create_token() {

		this.authenticateRepository.create(mockUser);

		var result = this.useCase.execute(mockUser.getEmail()
				.getValue(), "123456");

		assertThat(result).isInstanceOf(String.class);
		assertThat(result).isEqualTo(mockUser.getId() + mockUser.getRole());

	}


}

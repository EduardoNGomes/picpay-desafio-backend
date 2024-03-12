package com.picpay_challenge.picpay_challenge.useCase.Authenticate;

import com.picpay_challenge.picpay_challenge.core.cryptography.Encrypt;
import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordCompare;
import com.picpay_challenge.picpay_challenge.core.exceptions.CredentialsInvalidException;
import com.picpay_challenge.picpay_challenge.core.repositories.AuthenticateRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Authenticate;
import com.picpay_challenge.picpay_challenge.domain.enums.Roles;
import com.picpay_challenge.picpay_challenge.domain.useCases.Authenticate.AuthenticateUseCase;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticateWithMocksTest {

	private Authenticate mockUser;
	@InjectMocks
	private AuthenticateUseCase useCase;
	@Mock
	private AuthenticateRepository authenticateRepository;
	@Mock
	private PasswordCompare passwordCompare;
	@Mock
	private Encrypt encrypt;

	@BeforeEach
	public void setup() {
		this.mockUser = new Authenticate(new UniqueEmail("jonhDoe@email.com"), "123456", Roles.USER, UUID.randomUUID()
				.toString());
	}


	@Test
	@DisplayName("Should not be able to create a token if email does not exist")
	public void should_not_be_able_to_create_a_token_if_email_does_not_exist() {

		final CredentialsInvalidException e = assertThrows(CredentialsInvalidException.class, () -> this.useCase.execute("johnDoe@email.com", "123456"));

		assertThat(e).isNotNull();
	}

	@Test
	@DisplayName("Should not be able to create a token if password is wrong exist")
	public void should_not_be_able_to_create_a_token_if_password_is_wrong() {

		when(authenticateRepository.findByEmail(ArgumentMatchers.any())).thenReturn(Optional.of(this.mockUser));

		when(passwordCompare.compare(ArgumentMatchers.anyString(), eq(this.mockUser.getPassword()))).thenReturn(false);

		final CredentialsInvalidException e = assertThrows(CredentialsInvalidException.class, () -> this.useCase.execute("johnDoe@email.com", "123456"));

		assertThat(e).isNotNull();
		verify(passwordCompare, times(1)).compare(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

		verifyNoInteractions(encrypt);
	}

	@Test
	@DisplayName("Should be able to create a token")
	public void should_be_able_to_create_a_token() {

		when(authenticateRepository.findByEmail(ArgumentMatchers.any())).thenReturn(Optional.of(this.mockUser));

		when(passwordCompare.compare(ArgumentMatchers.anyString(), eq(this.mockUser.getPassword()))).thenReturn(true);

		when(encrypt.create(this.mockUser.getId(), this.mockUser.getRole())).thenReturn(anyString());

		var result = useCase.execute(mockUser.getEmail()
				.toString(), mockUser.getPassword());

		assertThat(result).isInstanceOf(String.class);

		verify(encrypt, times(1)).create(this.mockUser.getId(), this.mockUser.getRole());

	}

}

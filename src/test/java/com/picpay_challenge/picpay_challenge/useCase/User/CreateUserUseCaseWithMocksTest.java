package com.picpay_challenge.picpay_challenge.useCase.User;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.UserAccountRepository;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.CreateUserUseCase;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.dto.CreateUserUseCaseDTO;
import com.picpay_challenge.picpay_challenge.factory.UserFactory;
import java.util.Optional;
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
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseWithMocksTest {

	@InjectMocks
	private CreateUserUseCase useCase;

	@Mock
	private UserRepository repository;

	@Mock
	private UserAccountRepository accountRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	private CreateUserUseCaseDTO user;

	@BeforeEach
	public void setup() {

		var MockUser = UserFactory.CreateUser(Optional.empty());

		this.user = CreateUserUseCaseDTO.builder().cpf(MockUser.getCpf())
				.email(MockUser.getEmail()).firstName(MockUser.getFirstName())
				.lastName(MockUser.getLastName())
				.password(MockUser.getPassword()).build();
	}

	@DisplayName("Should be able to create a new user")
	@Test
	public void should_be_able_to_create_a_new_user() {

		var result = this.useCase.execute(this.user);
		assertThat(result).isEqualTo("created");

	}

	@DisplayName("Should not be able to create a new user if user already exist")
	@Test
	public void should_be_not_able_to_create_a_new_user_if_user_already_exist() {

		when(repository.findByEmailOrCpf(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(Optional.of(UserFactory.CreateUser(Optional.empty())));

		final UserAlreadyExistException e = assertThrows(UserAlreadyExistException.class, () -> {
			this.useCase.execute(this.user);
		});

		assertThat(e).isNotNull();

		verifyNoInteractions(passwordEncoder);

		verifyNoInteractions(accountRepository);


	}

}

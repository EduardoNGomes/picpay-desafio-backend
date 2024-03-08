package com.picpay_challenge.picpay_challenge.useCase.User;

import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlreadyExistException;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.CreateUserUseCase;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.dto.CreateUserUseCaseDTO;
import com.picpay_challenge.picpay_challenge.test.cryptography.MockPasswordEncoder;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateUserUseCaseTest {

	private final CreateUserUseCase useCase;

	private final InMemoryUserRepository repository;
	private CreateUserUseCaseDTO userToCreate;

	public CreateUserUseCaseTest() {

		MockPasswordEncoder mockPasswordEncoder = new MockPasswordEncoder();
		this.repository = new InMemoryUserRepository();
		this.useCase = new CreateUserUseCase(repository, mockPasswordEncoder);
	}

	@BeforeEach
	public void setup() {

		this.userToCreate = CreateUserUseCaseDTO.builder().cpf("12345678")
				.email("johnDoe@hotmail.com").firstName("John").lastName("Doe")
				.password("123456").build();
	}

	@DisplayName("Should be able to create a new user")
	@Test
	public void should_be_able_to_create_a_new_user() {

		var result = this.useCase.execute(this.userToCreate);

		assertThat(result).isEqualTo("created");
		assertThat(repository.items.size()).isEqualTo(1);

		assertThat(repository.items.getFirst()
				.getPassword()).isEqualTo("123456-hashed");
	}

	@DisplayName("Should not be able to create a new user")
	@Test
	public void should_not_be_able_to_create_with_email_or_cpf_already_created() {

		this.useCase.execute(this.userToCreate);

		final UserAlreadyExistException e = assertThrows(UserAlreadyExistException.class, () -> this.useCase.execute(this.userToCreate));

		assertThat(e).isNotNull();
	}

}

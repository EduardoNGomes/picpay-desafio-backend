package com.picpay_challenge.picpay_challenge.useCase.User;

import com.picpay_challenge.picpay_challenge.core.exceptions.UserAlredyExistException;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.CreateUserUseCase;
import com.picpay_challenge.picpay_challenge.domain.useCases.User.dto.CreateUserUseCaseDTO;
import com.picpay_challenge.picpay_challenge.test.cryptography.MockPasswordEncoder;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemoryUserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateUserUseCaseTest {

	private final CreateUserUseCase createUserUseCase;

	private final InMemoryUserRepository inMemoryUserRepository;
	private CreateUserUseCaseDTO userToCreate;

	public CreateUserUseCaseTest() {

		MockPasswordEncoder mockPasswordEncoder = new MockPasswordEncoder();
		this.inMemoryUserRepository = new InMemoryUserRepository();
		this.createUserUseCase = new CreateUserUseCase(inMemoryUserRepository, mockPasswordEncoder);
	}

	@BeforeEach
	public void setUp() {

		this.userToCreate = CreateUserUseCaseDTO.builder().cpf("12345678")
				.email("johnDoe@hotmail.com").firstName("John").lastName("Doe")
				.password("123456").build();
	}

	@DisplayName("Should be able to create a new user")
	@Test
	public void should_be_able_to_create_a_new_user() {

		this.createUserUseCase.execute(this.userToCreate);

		AssertionsForClassTypes.assertThat(inMemoryUserRepository.items.size())
				.isEqualTo(1);

		AssertionsForClassTypes.assertThat(inMemoryUserRepository.items.getFirst()
				.getPassword()).isEqualTo("123456-hashed");
	}

	@Test
	public void should_not_be_able_to_create_with_email_or_cpf_already_created() {

		this.createUserUseCase.execute(this.userToCreate);

		try {
			this.createUserUseCase.execute(this.userToCreate);
		} catch (Exception e) {
			AssertionsForClassTypes.assertThat(e)
					.isInstanceOf(UserAlredyExistException.class);
		}
	}

}
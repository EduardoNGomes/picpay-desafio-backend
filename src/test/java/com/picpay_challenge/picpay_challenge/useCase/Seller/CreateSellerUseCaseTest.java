package com.picpay_challenge.picpay_challenge.useCase.Seller;

import com.picpay_challenge.picpay_challenge.core.exceptions.SellerAlreadyExistException;
import com.picpay_challenge.picpay_challenge.domain.user.application.Seller.CreateSellerUseCase;
import com.picpay_challenge.picpay_challenge.domain.user.application.Seller.dto.CreateSellerUseCaseDTO;
import com.picpay_challenge.picpay_challenge.test.cryptography.MockPassword;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemoryAccountRepository;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemorySellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateSellerUseCaseTest {
	private final CreateSellerUseCase useCase;

	private final InMemorySellerRepository repository;

	private final InMemoryAccountRepository accountRepository;


	private CreateSellerUseCaseDTO sellerToCreate;

	public CreateSellerUseCaseTest() {

		MockPassword mockPassword = new MockPassword();
		this.repository = new InMemorySellerRepository();
		this.accountRepository = new InMemoryAccountRepository();
		this.useCase = new CreateSellerUseCase(repository, mockPassword, accountRepository);
	}

	@BeforeEach
	public void setup() {

		this.sellerToCreate = CreateSellerUseCaseDTO.builder().cnpj("12345678")
				.email("johnDoe@hotmail.com").firstName("John").lastName("Doe")
				.password("123456").build();
	}

	@DisplayName("Should be able to create a new seller")
	@Test
	public void should_be_able_to_create_a_new_seller() {

		var result = this.useCase.execute(this.sellerToCreate);

		assertThat(result).isEqualTo("created");
		assertThat(repository.getItems().size()).isEqualTo(1);
		assertThat(accountRepository.getItems().size()).isEqualTo(1);

		assertThat(repository.getItems().getFirst()
				.getPassword()).isEqualTo("123456-hashed");
	}

	@DisplayName("Should not be able to create a new seller if the seller already exist")
	@Test
	public void should_not_be_able_to_create_with_email_or_cnpj_already_created() {

		this.useCase.execute(this.sellerToCreate);

		final SellerAlreadyExistException e = assertThrows(SellerAlreadyExistException.class, () -> this.useCase.execute(this.sellerToCreate));

		assertThat(e).isNotNull();
	}

}

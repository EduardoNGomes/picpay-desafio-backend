package com.picpay_challenge.picpay_challenge.useCase.Seller;

import com.picpay_challenge.picpay_challenge.core.cryptography.PasswordEncoder;
import com.picpay_challenge.picpay_challenge.core.exceptions.SellerAlreadyExistException;
import com.picpay_challenge.picpay_challenge.core.repositories.SellerRepository;
import com.picpay_challenge.picpay_challenge.domain.useCases.Seller.CreateSellerUseCase;
import com.picpay_challenge.picpay_challenge.domain.useCases.Seller.dto.CreateSellerUseCaseDTO;
import com.picpay_challenge.picpay_challenge.factory.SellerFactory;
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
public class CreateSellerUseCaseWithMocksTest {
	@InjectMocks
	private CreateSellerUseCase useCase;

	@Mock
	private SellerRepository repository;

	@Mock
	private PasswordEncoder passwordEncoder;

	private CreateSellerUseCaseDTO seller;

	@BeforeEach
	public void setup() {

		var MockSeller = SellerFactory.CreateSeller(Optional.empty());

		this.seller = CreateSellerUseCaseDTO.builder()
				.cnpj(MockSeller.getCnpj()).email(MockSeller.getEmail())
				.firstName(MockSeller.getFirstName())
				.lastName(MockSeller.getLastName())
				.password(MockSeller.getPassword()).build();
	}

	@DisplayName("Should be able to create a new seller")
	@Test
	public void should_be_able_to_create_a_new_seller() {

		var result = this.useCase.execute(this.seller);
		assertThat(result).isEqualTo("created");

	}

	@DisplayName("Should not be able to create a new seller if user already exist")
	@Test
	public void should_be_not_able_to_create_a_new_seller_if_user_already_exist() {

		when(repository.findByEmailOrCnpj(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(Optional.of(SellerFactory.CreateSeller(Optional.empty())));

		final SellerAlreadyExistException e = assertThrows(SellerAlreadyExistException.class, () -> this.useCase.execute(this.seller));

		assertThat(e).isNotNull();

		verifyNoInteractions(passwordEncoder);

	}

}

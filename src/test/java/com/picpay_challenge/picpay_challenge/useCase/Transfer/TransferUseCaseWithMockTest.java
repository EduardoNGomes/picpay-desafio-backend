package com.picpay_challenge.picpay_challenge.useCase.Transfer;

import com.picpay_challenge.picpay_challenge.core.exceptions.InsufficientBalanceException;
import com.picpay_challenge.picpay_challenge.core.exceptions.PermissionDeniedException;
import com.picpay_challenge.picpay_challenge.core.exceptions.ResourceNotFound;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.core.validation.ApproveTransfer;
import com.picpay_challenge.picpay_challenge.domain.useCases.Transfer.TransferUseCase;
import com.picpay_challenge.picpay_challenge.factory.AccountFactory;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferUseCaseWithMockTest {

	@InjectMocks
	private TransferUseCase useCase;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private ApproveTransfer approveTransfer;

	@DisplayName("Should not be able to transfer money if account is seller")
	@Test
	public void should_not_be_able_transfer_money_if_account_is_seller() {

		var e = assertThrows(PermissionDeniedException.class, () -> useCase.execute("", "SELLER", 0.0, ""));

		assertThat(e).isNotNull();
		assertThat(e.getMessage()).isEqualTo("Permission denied to do this action. Seller account can not transfer money");

		verifyNoInteractions(accountRepository);
		verifyNoInteractions(approveTransfer);

	}

	@DisplayName("Should not be able to transfer money if account is not USER")
	@Test
	public void should_not_be_able_transfer_money_if_account_is_not_USER() {

		var e = assertThrows(PermissionDeniedException.class, () -> useCase.execute("", "", 0.0, ""));

		assertThat(e).isNotNull();
		assertThat(e.getMessage()).isEqualTo("Permission denied to do this action. Account type is invalid");
		verifyNoInteractions(accountRepository);

		verifyNoInteractions(approveTransfer);


	}

	@DisplayName("Should not be able to transfer money if account is not exist")
	@Test
	public void should_not_be_able_transfer_money_if_account_is_not_exist() {

		var e = assertThrows(ResourceNotFound.class, () -> useCase.execute(UUID.randomUUID()
				.toString(), "USER", 0.0, UUID.randomUUID().toString()));

		assertThat(e).isNotNull();

		verify(accountRepository, times(2)).findByOwnerId(ArgumentMatchers.any());

		verifyNoInteractions(approveTransfer);


	}


	@DisplayName("Should not be able to transfer money if account does not have money")
	@Test
	public void should_not_be_able_transfer_money_if_account_does_not_have_money() {

		var MockAccount = AccountFactory.CreateAccount(Optional.empty(), Optional.empty(), Optional.empty());

		when(accountRepository.findByOwnerId(ArgumentMatchers.any())).thenReturn(Optional.of(MockAccount));

		var e = assertThrows(InsufficientBalanceException.class, () -> useCase.execute(UUID.randomUUID()
				.toString(), "USER", 10.0, UUID.randomUUID().toString()));

		assertThat(e).isNotNull();

		verifyNoInteractions(approveTransfer);
	}

	@DisplayName("Should not be able to transfer money if bank approved is false")
	@Test
	public void should_not_be_able_transfer_money_if_bank_approved_is_false() {

		var MockAccount = AccountFactory.CreateAccount(Optional.empty(), Optional.of(10.00), Optional.empty());

		when(accountRepository.findByOwnerId(ArgumentMatchers.any())).thenReturn(Optional.of(MockAccount));
		when(approveTransfer.confirm()).thenReturn(false);

		var e = assertThrows(PermissionDeniedException.class, () -> useCase.execute(UUID.randomUUID()
				.toString(), "USER", 10.0, UUID.randomUUID().toString()));

		assertThat(e).isNotNull();

	}

	@DisplayName("Should not be able to transfer money between accounts")
	@Test
	public void should_be_able_transfer_money_between_accounts() {

		var MockAccount = AccountFactory.CreateAccount(Optional.empty(), Optional.of(10.00), Optional.empty());

		when(accountRepository.findByOwnerId(ArgumentMatchers.any())).thenReturn(Optional.of(MockAccount));
		when(approveTransfer.confirm()).thenReturn(true);

		useCase.execute(UUID.randomUUID()
				.toString(), "USER", 10.0, UUID.randomUUID().toString());

		verify(approveTransfer, times(1)).confirm();
		verify(accountRepository, times(1)).decreaseBalance(eq(10.0), ArgumentMatchers.any());
		verify(accountRepository, times(1)).increaseBalance(eq(10.0), ArgumentMatchers.any());

	}


}

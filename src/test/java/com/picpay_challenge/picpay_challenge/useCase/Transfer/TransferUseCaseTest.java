package com.picpay_challenge.picpay_challenge.useCase.Transfer;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.validation.ApproveTransfer;
import com.picpay_challenge.picpay_challenge.domain.entities.Account;
import com.picpay_challenge.picpay_challenge.domain.useCases.Transfer.TransferUseCase;
import com.picpay_challenge.picpay_challenge.factory.AccountFactory;
import com.picpay_challenge.picpay_challenge.test.repositories.InMemoryAccountRepository;
import com.picpay_challenge.picpay_challenge.test.validation.MockApproveTransfer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransferUseCaseTest {

	UUID idAccountFrom;
	UUID idOwnerFrom;
	UUID idAccountTo;
	UUID idOwnerTo;
	private TransferUseCase useCase;
	private InMemoryAccountRepository accountRepository;


	public TransferUseCaseTest() {

	}

	@BeforeEach
	public void setup() {
		this.idAccountFrom = UUID.randomUUID();
		this.idOwnerFrom = UUID.randomUUID();

		this.idAccountTo = UUID.randomUUID();
		this.idOwnerTo = UUID.randomUUID();

		ApproveTransfer approveTransfer = new MockApproveTransfer();
		this.accountRepository = new InMemoryAccountRepository();

		var MockAccountFrom = AccountFactory.CreateAccount(Optional.of(idAccountFrom), Optional.of(10.00), Optional.of(idOwnerFrom));

		var MockAccountTo = AccountFactory.CreateAccount(Optional.of(idAccountTo), Optional.of(10.00), Optional.of(idOwnerTo));

		List<Account> MockItems = new ArrayList<>();

		MockItems.add(MockAccountTo);
		MockItems.add(MockAccountFrom);

		this.accountRepository.setItems(MockItems);

		this.useCase = new TransferUseCase(this.accountRepository, approveTransfer);


	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@DisplayName("Should not be able to transfer money between accounts")
	@Test
	public void should_be_able_transfer_money_between_accounts() {

		useCase.execute(idOwnerFrom.toString(), "USER", 10.0, idOwnerTo.toString());

		var accountFrom = this.accountRepository.findById(new UniqueEntityID(Optional.of(idAccountFrom)));

		var accountTo = this.accountRepository.findById(new UniqueEntityID(Optional.of(idAccountTo)));

		assertThat(accountFrom.get().getBalance()).isEqualTo(0.0);
		assertThat(accountTo.get().getBalance()).isEqualTo(20.0);

	}

}

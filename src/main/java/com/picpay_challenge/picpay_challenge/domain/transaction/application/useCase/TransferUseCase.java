package com.picpay_challenge.picpay_challenge.domain.transaction.application.useCase;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.exceptions.InsufficientBalanceException;
import com.picpay_challenge.picpay_challenge.core.exceptions.PermissionDeniedException;
import com.picpay_challenge.picpay_challenge.core.exceptions.ResourceNotFound;
import com.picpay_challenge.picpay_challenge.core.repositories.AccountRepository;
import com.picpay_challenge.picpay_challenge.core.validation.ApproveTransfer;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.enums.Roles;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferUseCase {

	private final AccountRepository accountRepository;

	private final ApproveTransfer approveTransfer;

	@Autowired
	public TransferUseCase(AccountRepository accountRepository, ApproveTransfer approveTransfer) {
		this.accountRepository = accountRepository;
		this.approveTransfer = approveTransfer;
	}


	public void execute(String userIdFrom, String roleUserFrom, Double value, String userIdTo) throws
			PermissionDeniedException, ResourceNotFound,
			InsufficientBalanceException {

		if (Roles.SELLER.getState().equals(roleUserFrom)) {
			throw new PermissionDeniedException("Seller account can not transfer money");
		}

		if (!roleUserFrom.equals("USER")) {
			throw new PermissionDeniedException("Account type is invalid");
		}

		var ownerAccountFromId = new UniqueEntityID(Optional.of(UUID.fromString(userIdFrom)));

		var ownerAccountFrom = accountRepository.findByOwnerId(ownerAccountFromId);

		var ownerAccountToId = new UniqueEntityID(Optional.of(UUID.fromString(userIdTo)));

		var ownerAccountTo = accountRepository.findByOwnerId(ownerAccountToId);

		if (ownerAccountFrom.isEmpty() | ownerAccountTo.isEmpty()) {
			throw new ResourceNotFound();
		}

		if (ownerAccountFrom.get().getBalance() < value) {
			throw new InsufficientBalanceException();
		}

		var approvedTransaction = approveTransfer.confirm();

		if (!approvedTransaction) {
			throw new PermissionDeniedException("");
		}

		var accountIdToDecrease = new UniqueEntityID(Optional.ofNullable(ownerAccountFrom.get()
				.getIdValue()));

		accountRepository.decreaseBalance(value, accountIdToDecrease);

		var accountIdToIncrease = new UniqueEntityID(Optional.ofNullable(ownerAccountTo.get()
				.getIdValue()));

		accountRepository.increaseBalance(value, accountIdToIncrease);

	}

}

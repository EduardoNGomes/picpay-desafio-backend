package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.SellerRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Seller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class InMemorySellerRepository implements SellerRepository {
	List<Seller> items = new ArrayList<>();

	@Override
	public Optional<Seller> findByEmailOrCnpj(UniqueEmail email, UniqueCNPJ cnpj) {

		return this.items.stream().filter(user -> user.getCnpj()
				.equals(cnpj.getValue()) | user.getEmail()
				.equals(email.getValue())).findFirst();

	}

	@Override
	public void create(Seller seller) {
		items.add(seller);
	}

	@Override
	public Optional<Seller> findById(UniqueEntityID id) {
		return this.items.stream()
				.filter(user -> id.equals(new UniqueEntityID(Optional.ofNullable(user.getIdValue()))))
				.findFirst();
	}

}

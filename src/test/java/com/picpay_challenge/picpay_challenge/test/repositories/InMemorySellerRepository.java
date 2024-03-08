package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.SellerRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Seller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemorySellerRepository implements SellerRepository {
	public List<Seller> items = new ArrayList<>();

	@Override
	public Optional<Seller> findByEmailOrCnpj(UniqueEmail email, UniqueCNPJ cnpj) {
		var userExist = this.items.stream().filter(user -> user.getCnpj()
				.equals(cnpj.getValue()) | user.getEmail()
				.equals(email.getValue())).toList();
		if (userExist.size() > 0) {
			return Optional.ofNullable(userExist.getFirst());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void create(Seller seller) {
		items.add(seller);
	}

	@Override
	public Seller findById(UniqueEntityID id) {
		return null;
	}

}

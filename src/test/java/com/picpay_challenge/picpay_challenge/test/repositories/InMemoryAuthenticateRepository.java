package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.repositories.AuthenticateRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Authenticate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class InMemoryAuthenticateRepository implements AuthenticateRepository {

	List<Authenticate> items = new ArrayList<>();

	@Override
	public Optional<Authenticate> findByEmail(UniqueEmail email) {

		var userExist = this.items.stream()
				.filter(user -> user.getEmail().equals(email)).toList();

		if (userExist.size() > 0) {
			return Optional.of(userExist.getFirst());
		} else {
			return Optional.empty();
		}

	}

	public void create(Authenticate user) {
		this.items.add(user);
	}

}

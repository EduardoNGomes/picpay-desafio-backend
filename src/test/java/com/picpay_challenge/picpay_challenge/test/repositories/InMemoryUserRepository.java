package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {

	public List<User> items = new ArrayList<>();

	@Override
	public Optional<User> findByEmailOrCpf(UniqueEmail email, UniqueCPF cpf) {

		var userExist = this.items.stream().filter(user -> user.getCpf()
				.equals(cpf.getValue()) | user.getEmail()
				.equals(email.getValue())).toList();
		if (userExist.size() > 0) {
			return Optional.ofNullable(userExist.getFirst());
		} else {
			return Optional.empty();
		}

	}

	@Override
	public void create(User user) {

		this.items.add(user);
	}

	@Override
	public User findById(UniqueEntityID id) {

		return null;
	}

}

package com.picpay_challenge.picpay_challenge.test.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.repositories.UserRepository;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class InMemoryUserRepository implements UserRepository {
	List<User> items = new ArrayList<>();

	@Override
	public Optional<User> findByEmailOrCpf(UniqueEmail email, UniqueCPF cpf) {

		return this.items.stream().filter(user -> user.getCpf()
				.equals(cpf.getValue()) | user.getEmail()
				.equals(email.getValue())).findFirst();


	}

	@Override
	public void create(User user) {

		this.items.add(user);
	}

	@Override
	public Optional<User> findById(UniqueEntityID id) {
		return this.items.stream()
				.filter(user -> id.equals(new UniqueEntityID(Optional.ofNullable(user.getIdValue()))))
				.findFirst();

	}

}

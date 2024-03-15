package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.User;
import java.util.Optional;

public interface UserRepository {

	Optional<User> findByEmailOrCpf(UniqueEmail email, UniqueCPF cpf);

	void create(User user);

	Optional<User> findById(UniqueEntityID id);

}

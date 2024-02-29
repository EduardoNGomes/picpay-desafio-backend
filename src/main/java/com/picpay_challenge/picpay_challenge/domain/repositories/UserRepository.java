package com.picpay_challenge.picpay_challenge.domain.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.domain.entities.User;
import com.picpay_challenge.picpay_challenge.domain.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.domain.vo.UniqueEmail;

public interface UserRepository {

	User findByEmailOrCpf(UniqueEmail email, UniqueCPF cpf);

	void create(User user);

	User findById(UniqueEntityID id);

}

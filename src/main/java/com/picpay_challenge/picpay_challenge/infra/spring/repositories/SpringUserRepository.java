package com.picpay_challenge.picpay_challenge.infra.spring.repositories;

import com.picpay_challenge.picpay_challenge.infra.spring.entities.UserEntityDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringUserRepository extends JpaRepository<UserEntityDatabase, UUID> {

	Optional<UserEntityDatabase> findByEmailOrCpf(String email, String cpf);

}
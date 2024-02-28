package com.picpay_challenge.picpay_challenge.infra.database.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay_challenge.picpay_challenge.infra.database.entities.UserEntityDatabase;

public interface UserRepository extends JpaRepository<UserEntityDatabase, UUID> {

  public Optional<UserEntityDatabase> findByEmailOrCpf(String email, String cpf);
}

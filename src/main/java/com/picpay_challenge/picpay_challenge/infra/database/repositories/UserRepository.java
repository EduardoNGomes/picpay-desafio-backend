package com.picpay_challenge.picpay_challenge.infra.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay_challenge.picpay_challenge.infra.database.entities.UserEntityToRepository;

public interface UserRepository extends JpaRepository<UserEntityToRepository, UUID> {

}

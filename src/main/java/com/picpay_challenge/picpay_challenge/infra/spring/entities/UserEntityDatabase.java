package com.picpay_challenge.picpay_challenge.infra.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntityDatabase {

	@Column(unique = true)
	private String cpf;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	@CreationTimestamp
	@Column(name = "create_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}

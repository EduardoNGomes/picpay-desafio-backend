package com.picpay_challenge.picpay_challenge.domain.useCases.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserUseCaseDTO {

	private String cpf;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}

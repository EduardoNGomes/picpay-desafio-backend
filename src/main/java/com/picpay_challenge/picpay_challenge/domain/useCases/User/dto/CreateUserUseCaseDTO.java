package com.picpay_challenge.picpay_challenge.domain.useCases.User.dto;

import lombok.Data;

@Data
public class CreateUserUseCaseDTO {

	private String cpf;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}

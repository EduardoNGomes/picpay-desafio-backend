package com.picpay_challenge.picpay_challenge.domain.user.application.Seller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSellerUseCaseDTO {

	private String cnpj;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}

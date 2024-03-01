package com.picpay_challenge.picpay_challenge.domain.interfaces;

import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IUserSeller {

	public String firstName;
	public String lastName;
	public UniqueEmail email;
	public String password;

}

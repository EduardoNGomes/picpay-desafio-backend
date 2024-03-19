package com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IAccount {
	public Double balance;
	public UniqueEntityID ownerID;

}

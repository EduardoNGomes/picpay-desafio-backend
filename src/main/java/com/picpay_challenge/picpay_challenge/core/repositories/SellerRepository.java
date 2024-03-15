package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCNPJ;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.entities.Seller;
import java.util.Optional;

public interface SellerRepository {

	Optional<Seller> findByEmailOrCnpj(UniqueEmail email, UniqueCNPJ cnpj);

	void create(Seller seller);

	Optional<Seller> findById(UniqueEntityID id);

}

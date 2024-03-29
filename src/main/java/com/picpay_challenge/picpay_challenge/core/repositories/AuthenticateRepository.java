package com.picpay_challenge.picpay_challenge.core.repositories;

import com.picpay_challenge.picpay_challenge.core.vo.UniqueEmail;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities.Authenticate;
import java.util.Optional;

public interface AuthenticateRepository {

	Optional<Authenticate> findByEmail(UniqueEmail email);

}

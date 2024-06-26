package com.picpay_challenge.picpay_challenge.domain.user.enterprise.entities;

import com.picpay_challenge.picpay_challenge.core.entities.Entity;
import com.picpay_challenge.picpay_challenge.core.entities.UniqueEntityID;
import com.picpay_challenge.picpay_challenge.core.events.EventDispatcher;
import com.picpay_challenge.picpay_challenge.core.vo.UniqueCPF;
import com.picpay_challenge.picpay_challenge.domain.notification.events.SendNotificationEvent;
import com.picpay_challenge.picpay_challenge.domain.notification.handler.SendNotificationEventHandler;
import com.picpay_challenge.picpay_challenge.domain.user.enterprise.interfaces.IUserSeller;
import java.util.Optional;

public class User extends Entity<IUserSeller> {

	private final UniqueCPF cpf;

	protected User(UniqueCPF cpf, IUserSeller props, Optional<UniqueEntityID> id) {

		super(props, id);
		this.cpf = cpf;
	}

	public static User create(UniqueCPF cpf, IUserSeller props, Optional<UniqueEntityID> id) {

		EventDispatcher.register(SendNotificationEvent.class.getName(), new SendNotificationEventHandler());

		return new User(cpf, props, id);

	}

	public String getCpf() {

		return this.cpf.getValue();
	}

	public String getFirstName() {

		return this.props.firstName;
	}

	public String getLastName() {

		return this.props.lastName;
	}

	public String getEmail() {

		return this.props.email.getValue();
	}

	public String getPassword() {

		return this.props.password;
	}

}

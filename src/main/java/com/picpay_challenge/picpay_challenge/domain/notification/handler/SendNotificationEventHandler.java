package com.picpay_challenge.picpay_challenge.domain.notification.handler;

import com.picpay_challenge.picpay_challenge.core.events.IEvent;
import com.picpay_challenge.picpay_challenge.core.events.IEventHandler;
import com.picpay_challenge.picpay_challenge.domain.notification.application.CreateUserNotificationUseCase;

public class SendNotificationEventHandler implements
		IEventHandler<CreateUserNotificationUseCase> {

	@Override
	public void handle(IEvent<CreateUserNotificationUseCase> event) {
		event.getEventData().execute();

	}

}

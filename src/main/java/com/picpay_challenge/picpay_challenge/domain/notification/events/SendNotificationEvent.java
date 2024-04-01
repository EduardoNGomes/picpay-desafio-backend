package com.picpay_challenge.picpay_challenge.domain.notification.events;

import com.picpay_challenge.picpay_challenge.core.events.IEvent;
import com.picpay_challenge.picpay_challenge.domain.notification.application.CreateUserNotificationUseCase;
import java.time.LocalDateTime;

public class SendNotificationEvent implements
		IEvent<CreateUserNotificationUseCase> {

	private final LocalDateTime dateTimeOccurred;
	private final CreateUserNotificationUseCase eventData;


	public SendNotificationEvent(CreateUserNotificationUseCase eventData) {
		this.dateTimeOccurred = LocalDateTime.now();
		this.eventData = eventData;
	}


	@Override
	public LocalDateTime getDateTimeOccurred() {
		return this.dateTimeOccurred;
	}

	@Override
	public CreateUserNotificationUseCase getEventData() {
		return this.eventData;
	}

}

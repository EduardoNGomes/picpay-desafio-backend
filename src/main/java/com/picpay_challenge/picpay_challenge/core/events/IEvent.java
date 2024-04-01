package com.picpay_challenge.picpay_challenge.core.events;

import java.time.LocalDateTime;

public interface IEvent {
	LocalDateTime dateTimeOccurred = null;
	Object eventData = null;

	LocalDateTime getDateTimeOccurred();

	Object getEventData();

}

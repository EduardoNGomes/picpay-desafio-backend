package com.picpay_challenge.picpay_challenge.core.events;

import java.time.LocalDateTime;

public interface IEvent<T> {


	LocalDateTime getDateTimeOccurred();

	T getEventData();

}

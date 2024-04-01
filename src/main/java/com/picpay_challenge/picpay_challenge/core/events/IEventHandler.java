package com.picpay_challenge.picpay_challenge.core.events;

public interface IEventHandler<T> {

	void handle(IEvent<T> event);

}

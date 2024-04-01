package com.picpay_challenge.picpay_challenge.core.events;

public interface IEventDispatcher {
	void register(String eventName, IEventHandler handler);

	void notify(IEvent event);

}

package com.picpay_challenge.picpay_challenge.core.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;

@Getter
public class EventDispatcher {
	//IEventDispatcher
	private static final HashMap<String, List<IEventHandler>> eventHandlers = new HashMap<>();


	public static void register(String eventName, IEventHandler handler) {

		if (!eventHandlers.containsKey(eventName)) {

			eventHandlers.put(eventName, new ArrayList<>());
		}

		eventHandlers.get(eventName).add(handler);


	}

	public static void notify(IEvent event) {
		var eventName = event.getClass().getName();

		if (eventHandlers.containsKey(eventName)) {
			eventHandlers.get(eventName)
					.forEach(handler -> handler.handle(event));
		}
	}


}

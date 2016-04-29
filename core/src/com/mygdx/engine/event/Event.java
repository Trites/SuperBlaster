package com.mygdx.engine.event;

import java.util.HashSet;
import java.util.Set;

/**
 * Event that can pass one parameter to its listener.
 * @param <T> Type of parameter to be passed.
 */
public class Event<T>
{
    private Set<EventListener<T>> listeners;

    public Event() {
	this.listeners = new HashSet<>();
    }

    public void subscribe(EventListener<T> listener){

	listeners.add(listener);
    }

    public void unsubscribe(EventListener<T> listener){

	listeners.remove(listener);
    }

    public void notify(T param){

	for(EventListener<T> listener : listeners)
	    listener.invoke(param);
    }
}

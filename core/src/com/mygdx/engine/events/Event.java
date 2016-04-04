package com.mygdx.engine.events;

import java.util.HashSet;
import java.util.List;

public class Event<T>
{
    HashSet<EventListener<T>> listeners;

    public Event() {
	this.listeners = new HashSet<EventListener<T>>();
    }

    public void subscribe(EventListener<T> listener){

	listeners.add(listener);
    }

    public void unsubscribe(EventListener<T> listener){

	listeners.remove(listener);
    }

    public void notify(T param){

	for(EventListener<T> listener : listeners)
	    listener.notify(param);
    }

    public void clear(){

	listeners.clear();
    }
}

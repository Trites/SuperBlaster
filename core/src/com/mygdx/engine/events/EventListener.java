package com.mygdx.engine.events;

/**
 * Basic event listener.
 * @param <T> Type of parameter passed when event is invoked.
 */
public interface EventListener<T>
{
    void invoke(T arg);
}

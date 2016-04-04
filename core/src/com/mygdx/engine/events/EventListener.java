package com.mygdx.engine.events;

public interface EventListener<T>
{
    void invoke(T arg);
}

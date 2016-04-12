package com.mygdx.engine.entity.managers;

import java.util.List;

public interface Manager<T>
{
    public abstract void add(T object);
    public abstract void add(List<T> objects);
    public abstract void remove(T object);
}

package com.mygdx.engine.entity;

/**
 * Interface for destroyable classes.
 */
@SuppressWarnings("unused")
//Needed by Manager<T>
public interface Destroyable
{
    public void destroy();
    public void dispose();
}

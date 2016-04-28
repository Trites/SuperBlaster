package com.mygdx.engine.entity.defaultcomponents;

/**
 * Interface for classes that can be rendered.
 * @param <T> Type of renderer
 */
public interface Renderable<T>
{
    void render(T renderer);
}

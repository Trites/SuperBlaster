package com.mygdx.engine.entity;

public abstract class Behaviour extends Component
{
    public Behaviour(final Entity entity) {
	super(entity);
    }

    public abstract void update(final float deltaTime);
}

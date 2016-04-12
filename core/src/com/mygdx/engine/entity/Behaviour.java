package com.mygdx.engine.entity;

public abstract class Behaviour extends Component implements Updatable
{
    public Behaviour(final Entity entity) {
	super(entity);
    }

    @Override
    public void destroy() {

    }
}

package com.mygdx.engine.entity;

public abstract class Behaviour extends Component implements Updatable
{
    protected Behaviour(final Entity entity) {
	super(entity);
    }

    protected Behaviour(final Entity entity, final boolean startActive) {
    	super(entity, startActive);
        }
}

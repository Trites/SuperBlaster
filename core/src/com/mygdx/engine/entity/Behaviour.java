package com.mygdx.engine.entity;

public abstract class Behaviour extends Component implements Updatable
{
    public Behaviour(final Entity entity) {
	super(entity);
    }

    public Behaviour(final Entity entity, final boolean startActive) {
    	super(entity, startActive);
        }
}

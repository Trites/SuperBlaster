package com.mygdx.engine.entity;

/**
 * A Behaviour is a component that is updated by the entity it belongs to. Most components will be derived from Behaviour.
 */
public abstract class Behaviour extends Component implements Updatable
{
    protected Behaviour(final Entity entity) {
	super(entity);
    }

    protected Behaviour(final Entity entity, final boolean startActive) {
    	super(entity, startActive);
        }
}

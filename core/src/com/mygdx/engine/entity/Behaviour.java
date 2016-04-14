package com.mygdx.engine.entity;

import com.mygdx.engine.entity.managers.ComponentManager;

public abstract class Behaviour extends Component implements Updatable
{
    public Behaviour(final Entity entity) {
	super(entity);
    }

    @Override
    public void destroy() {

    }
}

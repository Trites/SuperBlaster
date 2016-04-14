package com.mygdx.engine.entity;

import com.mygdx.engine.entity.managers.ComponentManager;

public abstract class ManagedComponent extends Component
{

    public ManagedComponent(final Entity entity) {
	super(entity);
    }

    public abstract void register(final ComponentManager world);

    public abstract void deregister(final ComponentManager world);

    @Override
    public void destroy() {

        deregister(getEntity().getComponentManager());
    }
}

package com.mygdx.engine.entity;

import com.mygdx.engine.entity.managers.ComponentManager;

/**
 * A ManagedComponent belongs to an entity but is updated by a ComponentManager,
 * ManagedComponents are derived into components that needs to be managed by their own system, e.g. physics and rendering.
 */
public abstract class ManagedComponent extends Component
{

    protected ManagedComponent(final Entity entity) {
	super(entity);
    }

    public abstract void register(final ComponentManager world);

    public abstract void deregister(final ComponentManager world);

    @Override
    public void destroy() {

        super.destroy();
        deregister(getEntity().getComponentManager());
    }
}

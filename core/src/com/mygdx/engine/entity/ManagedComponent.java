package com.mygdx.engine.entity;

import com.mygdx.engine.entity.managers.World;

public abstract class ManagedComponent extends Component
{


    public ManagedComponent(final Entity entity) {
	super(entity);
    }

    public abstract void register(final World world);

    @Override
    public void destroy() {

    }
}

package com.mygdx.game.entities.component.util;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.component.defaultcomponent.CollisionComponent;

/**
 * Base for a behaviour that invokes a collision response.
 */
public abstract class OnCollision extends Behaviour
{

    protected OnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);

	getEntity().collisionEvent.subscribe(this::collisionResponse);
    }

    protected abstract void collisionResponse(CollisionComponent other);

    @Override
    public void dispose() {
	super.dispose();
    	getEntity().collisionEvent.unsubscribe(this::collisionResponse);
    }
}

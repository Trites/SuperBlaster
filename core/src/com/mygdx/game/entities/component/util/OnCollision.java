package com.mygdx.game.entities.component.util;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.component.defaultcomponent.CollisionComponent;
import com.mygdx.engine.event.EventListener;

/**
 * Base for a behaviour that invokes a collision response.
 */
public abstract class OnCollision extends Behaviour
{
    private EventListener<CollisionComponent> collisionResponseHandler;

    protected OnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);

	collisionResponseHandler = this::collisionResponse;
	getEntity().collisionEvent.subscribe(collisionResponseHandler);
    }

    protected abstract void collisionResponse(CollisionComponent other);

    @Override
    public void dispose() {
	super.dispose();
    	getEntity().collisionEvent.unsubscribe(collisionResponseHandler);
    }
}

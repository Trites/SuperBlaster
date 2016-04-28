package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.events.EventListener;

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
    public void destroyImmediate() {
	super.destroyImmediate();
    	getEntity().collisionEvent.unsubscribe(collisionResponseHandler);
    }
}

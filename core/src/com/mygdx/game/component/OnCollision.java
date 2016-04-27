package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.events.EventListener;

public abstract class OnCollision extends Behaviour
{
    private EventListener<CollisionComponent> collisionResponseHandler;

    public OnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);

	collisionResponseHandler = (x)->collisionResponse(x);
	getEntity().collisionEvent.subscribe(collisionResponseHandler);
    }

    @Override
    public void update(final float deltaTime) {

    }

    protected abstract void collisionResponse(CollisionComponent other);

    @Override
    public void destroyImmediate() {
	super.destroyImmediate();
    	getEntity().collisionEvent.unsubscribe(collisionResponseHandler);
    }
}

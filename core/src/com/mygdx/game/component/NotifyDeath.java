package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.events.Event;

/**
 * Behaviour that invokes an event when destroyed.
 */
public class NotifyDeath extends Behaviour
{
    /**
     * Event is invoked when component is destroyed.
     * Sends the Transform of the destroyed component.
     */
    public Event<Transform> deathEvent;

    public NotifyDeath(final Entity entity) {
	super(entity);
	deathEvent = new Event<>();
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void destroy() {
	super.destroy();
    	deathEvent.notify(getTransform());
    }
}

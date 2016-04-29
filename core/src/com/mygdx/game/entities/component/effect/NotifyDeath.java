package com.mygdx.game.entities.component.effect;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.event.Event;

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
    public void dispose() {
	super.dispose();
	deathEvent.notify(getTransform());
    }


}

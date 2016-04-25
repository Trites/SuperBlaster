package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.events.Event;

public class NotifyDeath extends Behaviour
{
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

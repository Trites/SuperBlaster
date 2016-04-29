package com.mygdx.game.entities.component.util;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;

/**
 * Base for a behaviour that invokes a method after a certain time.
 * Can easily be extended to reset or delete after invoke.
 */
public abstract class Timer extends Behaviour
{
    private boolean tick;
    private float timer;

    protected Timer(final Entity entity, final float time) {
	super(entity);
	tick = true;
	timer = time;
    }

    @Override
    public void update(final float deltaTime) {

	if(tick){

	    timer -= deltaTime;

	    if(timer <= 0){

		timer = 0;
		tick = false;
		invoke();
	    }
	}

    }

    protected abstract void invoke();

    public void reset(final float time){

	this.timer = time;
	tick = timer > 0.0f;
    }
}

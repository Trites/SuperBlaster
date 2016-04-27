package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;

public abstract class Timer extends Behaviour
{
    private boolean tick;
    private float timer;

    public Timer(final Entity entity, final float time) {
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

    public float getTimer() {
	return timer;
    }

    public void setTimer(final float timer) {
	this.timer = timer;

	tick = timer > 0f;
    }

    public boolean isTick() {
	return tick;
    }

    public void setTick(final boolean tick) {

	this.tick = tick;

	if(timer <= 0f)
	    this.tick = false;
    }
}

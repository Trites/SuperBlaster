package com.mygdx.game.component.controller;

import com.mygdx.engine.entity.Entity;

public class MissileController extends EnemyController
{
    private static final float ACCELERATION = 2000f;
    private static final float MAX_VELOCITY = 400f;

    public MissileController(final Entity entity, final String targetTag) {
	super(entity, targetTag);
    }

    @Override
    public void update(final float deltaTime) {

	getTransform().turnTowards(target.getPosition(), 2f * deltaTime);
	accelerate(getTransform().getForwardVector(), ACCELERATION, MAX_VELOCITY, deltaTime);
    }

}

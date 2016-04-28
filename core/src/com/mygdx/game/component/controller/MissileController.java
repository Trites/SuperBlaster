package com.mygdx.game.component.controller;

import com.mygdx.engine.entity.Entity;

/**
 * Controller that tries to hit the given target. Has a high velocity and large turn radius.
 */
public class MissileController extends EnemyController
{
    /**
     * Acceleration per second.
     */
    private static final float ACCELERATION = 2000.0f;
    /**
     * Maximum velocity.
     */
    private static final float MAX_VELOCITY = 400.0f;
    /**
     * The speed at which the missile turns.
     * A turnfactor of 1/deltaTime will result in an instant rotation.
     */
    public static final float TURN_FACTOR = 2.0f;

    public MissileController(final Entity entity, final String targetTag) {
	super(entity, targetTag);
    }

    @Override
    public void update(final float deltaTime) {

	getTransform().turnTowards(target.getPosition(), TURN_FACTOR * deltaTime);
	accelerate(getTransform().getForwardVector(), ACCELERATION, MAX_VELOCITY, deltaTime);
    }

}

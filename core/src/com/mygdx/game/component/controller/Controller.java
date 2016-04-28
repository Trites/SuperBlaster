package com.mygdx.game.component.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

/**
 * Base for ControllerComponents which can be used by Player or AI to controll Entities.
 */
public abstract class Controller extends Behaviour
{
    protected RigidBody body = null;

    protected Controller(final Entity entity) {
	super(entity);

	getEntity().requireComponent(RigidBody.class);
    }

    @Override
    public void start() {
	super.start();
    	body = getComponent(RigidBody.class);
    }

    protected void accelerate(final Vector2 direction, final float amount, final float max, final float deltaTime){

	body.addVelocity(new Vector2(amount * direction.x * deltaTime, amount * direction.y * deltaTime));
	body.limitVelocity(max);
    }
}

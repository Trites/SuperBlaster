package com.mygdx.game.component.controller;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;

public class FollowController extends EnemyController
{
    private static final float ACCELERATION = 2000f;

    private final float maxVelocity;

    public FollowController(final Entity entity, final String targetTag, final float maxVelocity) {
	super(entity, targetTag);

	this.maxVelocity = maxVelocity;
	getEntity().collisionEvent.subscribe((x)->collisionEvent(x));
    }

    @Override
    public void start() {
	super.start();

    }

    @Override
    public void update(final float deltaTime) {

	getTransform().lookAt(target.getPosition());
	accelerate(getTransform().getForwardVector(), ACCELERATION, maxVelocity, deltaTime);
    }

    public void collisionEvent(final CollisionComponent other){



	//getEntity().destroy();
    }
}

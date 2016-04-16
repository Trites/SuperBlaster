package com.mygdx.game.component;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class DestroyWhenSlow extends Behaviour
{
    private RigidBody body;
    private float minVelocity;

    public DestroyWhenSlow(final Entity entity, final float minVelocity) {
	super(entity);
	getEntity().requireComponent(RigidBody.class);
	this.minVelocity = minVelocity;
    }

    @Override
    public void start() {
	super.start();
    	body = getComponent(RigidBody.class);
    }

    @Override
    public void update(final float deltaTime) {

	if(body.getVelocity().len() < minVelocity)
	    getEntity().destroy();
    }
}

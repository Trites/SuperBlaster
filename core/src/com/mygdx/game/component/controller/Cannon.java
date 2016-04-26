package com.mygdx.game.component.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;
import com.mygdx.game.factory.EntityBlueprints;

public class Cannon extends Behaviour
{
    private static float COOLDOWN_TIME = 0.1f;
    private static float ACCURACY = 0.97f;

    private int supply = 1;
    private float cooldownTimer = 0;

    private RigidBody body;

    public Cannon(final Entity entity) {
	super(entity);

	cooldownTimer = COOLDOWN_TIME;
	getEntity().requireComponent(RigidBody.class);
    }

    @Override
    public void start() {

	super.start();

	body = getComponent(RigidBody.class);
    }

    @Override
    public void update(final float deltaTime) {

	if(supply == 0){

	    if(cooldownTimer <= 0){

		supply = 1;
		cooldownTimer = COOLDOWN_TIME;
	    }else{

		cooldownTimer -= deltaTime;
	    }
	}
    }

    public void fire(){

	if(supply > 0){

	    RigidBody projBody = EntityBlueprints.instantiateBasicProjectile(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()))).getComponent(RigidBody.class);

	    Vector2 forward = getTransform().getForwardVector();
	    float angle = (float)(Math.atan2(forward.y, forward.x) + (Math.random() * (1 - ACCURACY) * 2*Math.PI - (1 - ACCURACY) * Math.PI));
	    forward = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));

	    projBody.addVelocity(new Vector2(0,0).mulAdd(forward, 1200));
	    body.addVelocity(new Vector2(0,0).mulAdd(forward, -20));
	    supply--;
	}
    }
}

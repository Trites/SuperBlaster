package com.mygdx.game.component.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.engine.util.Util;
import com.mygdx.game.factory.EntityBlueprints;

public class BeamCannon extends Behaviour
{
    private static float COOLDOWN_TIME = 1f;
    private static float ACCURACY = 0.99f;

    private  boolean fired;
    private int supply = 100;
    private float cooldownTimer = 0;

    private RigidBody body;

    public BeamCannon(final Entity entity) {
	super(entity);

	cooldownTimer = COOLDOWN_TIME;
	getEntity().requireComponent(RigidBody.class);
    }

    @Override
    public void start() {

	super.start();

	body = getComponent(RigidBody.class);
	fired = false;
    }

    @Override
    public void update(final float deltaTime) {

	if(fired && supply > 0){


	    body.addVelocity(new Vector2(0,0).mulAdd(getTransform().getForwardVector(), -80));
	    for(int i = 0; i < 5; i++){

		spawnProjectile();
		supply--;
	    }
	}

	if(supply <= 0){
	    fired = false;
	    if(cooldownTimer <= 0){

		supply = 100;
		cooldownTimer = COOLDOWN_TIME;
	    }else{

		cooldownTimer -= deltaTime;
	    }
	}
    }

    public void fire(){

	if(supply > 0){

	    //supply--;
	    fired = true;
	    CameraEffects.CameraShake(8f, 0.5f);
	}
    }

    private void spawnProjectile(){

	RigidBody projBody = EntityBlueprints.instantiateBasicProjectile(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()))).getComponent(RigidBody.class);

 	Vector2 forward = getTransform().getForwardVector();
 	float angle = (float)(Math.atan2(forward.y, forward.x) + (Math.random() * (1 - ACCURACY) * 2*Math.PI - (1 - ACCURACY) * Math.PI));
 	forward = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));

	projBody.getComponent(SpriteComponent.class).setColor(Util.randomColor(1f));
 	projBody.addVelocity(new Vector2(0,0).mulAdd(forward, (float)Math.random()*200 + 2000));
    }
}

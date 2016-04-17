package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.game.factory.EntityFactory;

public class Cannon extends Behaviour
{
    private static float COOLDOWN_TIME = 0.1f;

    private int supply = 1;
    private float cooldownTimer = 0;

    private RigidBody body;

    public Cannon(final Entity entity) {
	super(entity);

	cooldownTimer = COOLDOWN_TIME;
    }

    @Override
    public void start() {
	super.start();
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

	    RigidBody projBody = EntityFactory.BasicProjectile(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()))).getComponent(RigidBody.class);

	    Vector2 forward = getTransform().getForwardVector();
	    projBody.addVelocity(new Vector2(0,0).mulAdd(forward, 1200));
	    supply--;
	}
    }
}

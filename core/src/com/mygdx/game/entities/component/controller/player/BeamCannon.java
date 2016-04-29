package com.mygdx.game.entities.component.controller.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.component.defaultcomponent.RigidBody;
import com.mygdx.engine.entity.component.defaultcomponent.SpriteComponent;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.engine.util.Util;
import com.mygdx.game.entities.EntityBlueprints;

/**
 * A cannon that will fire 100 colorfull projectiles and produce a powerfull kickback.
 */
public class BeamCannon extends Behaviour
{
    /**
     * Cooldown in seconds during which the cannon cannot be fired again.
     */
    private static final float COOLDOWN_TIME = 1.0f;

    /**
     * Accuracy of cannon. Value of 1 gives perfect accuracy while 0 will cause the cannon to fire in 360 degrees randomly.
     */
    private static final float ACCURACY = 0.99f;
    /**
     * Force applied to the RigidBody of the owner entity when the cannon is fired.
     */
    public static final float KICKBACK = -80.0f;
    /**
     * Magnitude of camera shake produces.
     */
    public static final float CAMERA_SHAKE_MAGNITUDE = 8.0f;
    /**
     * Duration of camera shake produces.
     */
    public static final float CAMERA_SHAKE_DURATION = 0.5f;
    /**
     * Variation of projectile velocity (0 - PROJECTILE_VEL_VARIATION)
     */
    public static final float PROJECTILE_VEL_VARIATION = 600.0f;
    /**
     * Projectile base velocity.
     */
    public static final float PROJECTILE_VEL_BASE = 1800.0f;

    private  boolean fired;
    private int supply = 100;
    private float cooldownTimer = 0;

    private RigidBody body = null;

    public BeamCannon(final Entity entity) {
	super(entity);

	cooldownTimer = COOLDOWN_TIME;
	getEntity().requireComponent(RigidBody.class);
	getEntity().requireComponent(SpriteComponent.class);
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


	    body.addVelocity(new Vector2(0,0).mulAdd(getTransform().getForwardVector(), KICKBACK));
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
	    CameraEffects.cameraShake(CAMERA_SHAKE_MAGNITUDE, CAMERA_SHAKE_DURATION);
	}
    }

    private void spawnProjectile(){

	RigidBody projBody = EntityBlueprints.instantiateBasicProjectile(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()))).getComponent(RigidBody.class);

 	Vector2 forward = getTransform().getForwardVector();
 	float angle = (float)(Math.atan2(forward.y, forward.x) + (Math.random() * (1 - ACCURACY) * 2*Math.PI - (1 - ACCURACY) * Math.PI));
 	Vector2 direction = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));

	projBody.getComponent(SpriteComponent.class).setColor(Util.randomColor(1.00f));
 	projBody.addVelocity(new Vector2(0,0).mulAdd(direction, (float)Math.random() * PROJECTILE_VEL_VARIATION +
								PROJECTILE_VEL_BASE));
    }
}

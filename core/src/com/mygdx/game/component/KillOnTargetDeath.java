package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.events.EventListener;
import com.mygdx.game.factory.ParticleFactory;

/**
 * Behaviour that destroys the entity when the target is destroyed.
 * Spawning a spectacular particle effect using the color of the SpriteComponent attached to the entity, if one is present.
 */
public class KillOnTargetDeath extends Behaviour
{

    /**
     * Radius around the current location at which the particles spawn.
     */
    public static final float PARTICLE_SPAWN_RADIUS = 30.0f;
    /**
     * Base velocity of particles.
     */
    public static final float PARTICLE_BASE_VEL = 9000.0f;
    /**
     * Particles further from the dying target will be slower.
     * A high DISTANCE_VEL_ADJUST results in this difference being smaller.
     */
    public static final float DISTANCE_VEL_ADJUST = 10.0f;
    /**
     * Mass used to simulate an impact the particles.
     */
    public static final float OTHER_MASS = 200.0f;
    /**
     * Variation in tha mass of the particle (0 - PARTICLE_MASS_SPAN)
     */
    public static final float PARTICLE_MASS_SPAN = 400.0f;
    /**
     * Base (minimum) value of particle mass.
     */
    public static final float PARTICLE_MASS_BASE = 5.0f;
    /**
     * Number of particles spawned.
     */
    public static final int PARTICLE_COUNT = 100;

    private String targetTag;
    private NotifyDeath target = null;
    private EventListener<Transform> targetDeathHandler;

    public KillOnTargetDeath(final Entity entity, final String targetTag) {
	super(entity);
	//getEntity().requireComponent(SpriteComponent.class);
	this.targetTag = targetTag;
	targetDeathHandler = this::onTargetDeath;
    }

    @Override
    public void start() {
	super.start();

	Entity targetEntity = getEntity().findEntity(targetTag).get(0);

	if(targetEntity.hasComponent(NotifyDeath.class)){
	    target = targetEntity.getComponent(NotifyDeath.class);
	    target.deathEvent.subscribe(targetDeathHandler);
	}else{

	    System.out.println("NO TARGET!");
	}

    }

    @Override
    public void update(final float deltaTime) {

    }



    private void onTargetDeath(final Transform targetTransform){

	if(getEntity().hasComponent(SpriteComponent.class)){

	    Vector2 diffVector = new Vector2(getTransform().getPosition()).sub(targetTransform.getPosition());
	    float distance = new Vector2(diffVector).len();
	    Vector2 direction = new Vector2(diffVector).nor();

	    ParticleFactory.directionalDeathParticle(getTransform().getPosition(), PARTICLE_SPAWN_RADIUS, new Vector2(0, 0), targetTransform.getPosition(),
						     direction.scl(PARTICLE_BASE_VEL / ((float)Math.sqrt(distance /
													 DISTANCE_VEL_ADJUST) + 1)),
						     OTHER_MASS, PARTICLE_MASS_SPAN, PARTICLE_MASS_BASE, getComponent(SpriteComponent.class).getColor(),
						     PARTICLE_COUNT);
	}


	getEntity().destroy();
    }

    @Override public void destroyImmediate() {
	super.destroyImmediate();

	if(target != null){

	    target.deathEvent.unsubscribe(targetDeathHandler);
	}
    }
}

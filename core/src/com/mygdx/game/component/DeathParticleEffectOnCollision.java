package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.factory.ParticleFactory;

/**
 * Spawns a particle effect on collision that acts as if the entity has been crushed by the other collider.
 */
public class DeathParticleEffectOnCollision extends OnCollision
{
    /**
     * Variation in tha mass of the particle (0 - PARTICLE_MASS_SPAN)
     */
    public static final int PARTICLE_MASS_SPAN = 50;
    /**
     * Base (minimum) value of particle mass.
     */
    public static final int PARTICLE_MASS_BASE = 5;
    /**
     * Number of particles spawned.
     */
    public static final int PARTICLE_COUNT = 50;
    /**
     * Magnitude of camera shake produced.
     */
    public static final float CAMERA_SHAKE_MAGNITUDE = 10.0f;
    /**
     * Duration of camera shake produced.
     */
    public static final float CAMERA_SHAKE_DURATION = 0.5f;

    public DeathParticleEffectOnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);
	getEntity().requireComponent(RigidBody.class);
	getEntity().requireComponent(CircleCollider.class);
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    protected void collisionResponse(final CollisionComponent other) {

	if(!isActive())
	    return;

	CircleCollider thisCircle = getComponent(CircleCollider.class);
	RigidBody otherBody = other.getComponent(RigidBody.class);

	float overlap = other.edgeDistance(getComponent(CircleCollider.class));
	Vector2 dirToOther = new Vector2(other.getTransform().getPosition()).sub(getTransform().getPosition()).nor();
	Vector2 colPoint = new Vector2(other.getTransform().getPosition()).add(new Vector2(dirToOther).scl(-overlap));

	ParticleFactory
		.directionalDeathParticle(getTransform().getPosition(), thisCircle.getRadius(), getComponent(RigidBody.class).getVelocity(), colPoint,
					  otherBody.getVelocity(), otherBody.getMass(), PARTICLE_MASS_SPAN, PARTICLE_MASS_BASE,
					  getComponent(SpriteComponent.class).getColor(), PARTICLE_COUNT);

	CameraEffects.cameraShake(CAMERA_SHAKE_MAGNITUDE, CAMERA_SHAKE_DURATION);
	getEntity().destroy();
    }
}

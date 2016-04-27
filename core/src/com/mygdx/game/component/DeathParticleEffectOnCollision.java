package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.factory.ParticleFactory;

public class DeathParticleEffectOnCollision extends OnCollision
{
    public DeathParticleEffectOnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);
	getEntity().requireComponent(RigidBody.class);
	getEntity().requireComponent(CircleCollider.class);
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
		.DirectionalDeathParticle(getTransform().getPosition(), thisCircle.getRadius(), getComponent(RigidBody.class).getVelocity(), colPoint,
					  otherBody.getVelocity(), otherBody.getMass(), 50, 5,
					  getComponent(SpriteComponent.class).getColor(), 50);

	CameraEffects.CameraShake(10f, 0.5f);
	getEntity().destroy();
    }
}

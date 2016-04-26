package com.mygdx.game.component.controller;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.events.EventListener;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.factory.ParticleFactory;

public class FollowController extends Behaviour
{
    private static final float ACCELERATION = 2000f;
    private static final float MAX_VELOCITY = 200f;

    private RigidBody body;
    private String targetTag;

    private Entity targetEntity;
    private Transform target;

    public FollowController(final Entity entity, String targetTag) {
	super(entity);

	getEntity().requireComponent(RigidBody.class);
	this.targetTag = targetTag;

	getEntity().collisionEvent.subscribe((x)->collisionEvent(x));
    }

    @Override
    public void start() {
	super.start();

	body = getComponent(RigidBody.class);

	if(body == null) System.out.println("Failed to get body!");

	targetEntity = findEntity(targetTag).get(0);
	target = targetEntity.getTransform();


    }

    @Override
    public void update(final float deltaTime) {

	//getTransform().lookAt(target.getPosition());
	getTransform().turnTowards(target.getPosition(), 1f * deltaTime);
	accelerate(getTransform().getForwardVector(), deltaTime);
	//accelerate(new Vector2(target.getX() - getTransform().getX(), target.getY() - getTransform().getY()), deltaTime);
    }

    private void accelerate(Vector2 direction, float deltaTime){

	body.addVelocity(new Vector2(ACCELERATION * direction.x * deltaTime, ACCELERATION * direction.y * deltaTime));
	body.limitVelocity(MAX_VELOCITY);
    }

    public void collisionEvent(final CollisionComponent other){


	CircleCollider thisCircle = getComponent(CircleCollider.class);
	RigidBody otherBody = other.getComponent(RigidBody.class);

	float overlap = other.edgeDistance(getComponent(CircleCollider.class));
	Vector2 dirToOther = new Vector2(other.getTransform().getPosition()).sub(getTransform().getPosition()).nor();
	Vector2 colPoint = new Vector2(other.getTransform().getPosition()).add(new Vector2(dirToOther).scl(-overlap));

	ParticleFactory.DirectionalDeathParticle(getTransform().getPosition(), thisCircle.getRadius(), body.getVelocity(), colPoint,
						 otherBody.getVelocity(), otherBody.getMass(), 50, 5,
						 getComponent(SpriteComponent.class).getColor(), 50);

	CameraEffects.CameraShake(10f, 0.5f);
	getEntity().destroy();
    }
}

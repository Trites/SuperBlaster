package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.widget.color.internal.VerticalChannelBar;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.particle.ParticleData;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.util.Util;
import com.mygdx.game.FragmentParticle;
import com.mygdx.game.factory.ParticleFactory;

public class FollowController extends Behaviour
{
    private static final float ACCELERATION = 2000f;
    private static final float MAX_VELOCITY = 200f;

    private RigidBody body;
    private String targetTag;
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
	target = findEntity(targetTag).get(0).getTransform();
    }

    @Override
    public void update(final float deltaTime) {

	getTransform().lookAt(target.getPosition());
	//accelerate(getTransform().getForwardVector(), deltaTime);
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

	getEntity().destroy();
    }

}

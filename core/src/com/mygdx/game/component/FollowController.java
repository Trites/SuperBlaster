package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.widget.color.internal.VerticalChannelBar;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
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


	RigidBody otherBody = other.getComponent(RigidBody.class);

	/*float thisVel = body.getVelocity().len();
	float otherVel = otherBody.getVelocity().len();
	Vector2 thisDir = body.getDirection();
	Vector2 otherDir = otherBody.getDirection();

	Vector2 line = new Vector2(other.getTransform().getPosition()).sub(getTransform().getPosition()).nor();

	float a1 = new Vector2(thisDir).dot(line);
	float a2 = new Vector2(otherDir).dot(line);

	float p = (2f * (a2-a1))/(body.getMass() + otherBody.getMass());



	Vector2 dv = new Vector2(line).scl(p*otherBody.getMass());

	System.out.println(dv);

	Vector2 v1 = new Vector2(body.getVelocity()).add(dv);

	System.out.println(v1);
	//body.setVelocity((new Vector2(body.getVelocity()).nor().add(dv)).scl(thisVel));*/

	//Vector2 v1 = Util.getBounceVelocity(getTransform().getPosition(), other.getTransform().getPosition(),
	//					body.getVelocity(), otherBody.getVelocity(), body.getMass(), otherBody.getMass());

	ParticleFactory.DirectionalDeathParticle(getTransform().getPosition(), body.getVelocity(), otherBody, getComponent(SpriteComponent.class).getColor(), 50);

	getEntity().destroy();
    }

}

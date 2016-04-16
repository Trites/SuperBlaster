package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.particle.ParticleData;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.game.FragmentParticle;

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

	lookAt(target.getPosition());
	accelerate(getTransform().getForwardVector(), deltaTime);
	//accelerate(new Vector2(target.getX() - getTransform().getX(), target.getY() - getTransform().getY()), deltaTime);
    }

    protected void lookAt(Vector2 point){

        getEntity().getTransform().setRotation((float)(Math.atan2(point.y - getTransform().getPosition().y, point.x - getTransform().getPosition().x) * 180/Math.PI));
    }

    private void accelerate(Vector2 direction, float deltaTime){

	body.addVelocity(new Vector2(ACCELERATION * direction.x * deltaTime, ACCELERATION * direction.y * deltaTime));
	body.limitVelocity(MAX_VELOCITY);
    }

    public void collisionEvent(final CollisionComponent other){

	for(int i = 0; i < 100; i++){

	    float angle = (float)Math.random() * 360;
	    float vel = ((float)Math.random() * 900) + 300;

	    Transform transform = new Transform(new Vector2(getTransform().getPosition()), new Vector2(1, 1), angle);
	    ParticleData data = new ParticleData(new Vector2(0, 0).mulAdd(transform.getForwardVector(), vel), Color.RED, 0.2f);

	    ParticleSystem.GetInstance().spawn("Plasma.png", transform, data, (x, y, z)-> FragmentParticle.update(x, y, z));
	}

	getEntity().destroy();
    }
}

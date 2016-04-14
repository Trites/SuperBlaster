package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

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
	accelerate(new Vector2(target.getX() - getTransform().getX(), target.getY() - getTransform().getY()), deltaTime);
    }

    protected void lookAt(Vector2 point){

        getEntity().getTransform().setRotation((float)(Math.atan2(getTransform().getPosition().y - point.y, getTransform().getPosition().x - point.x) * 180/Math.PI));
    }

    private void accelerate(Vector2 direction, float deltaTime){

	body.addVelocity(new Vector2(ACCELERATION * direction.x * deltaTime, ACCELERATION * direction.y * deltaTime));
	body.limitVelocity(MAX_VELOCITY);
    }

    public void collisionEvent(final CollisionComponent other){

	getEntity().destroy();
    }
}

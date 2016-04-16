package com.mygdx.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class PlayerController extends Behaviour
{

    private static final float ACCELERATION = 2000f;
    private static final float MAX_VELOCITY = 400f;
    private static final float FRICTION = 0.02f;

    private Vector2 direction = Vector2.Zero;

    private RigidBody body;
    private Cannon cannon;

    public PlayerController(final Entity entity) {
	super(entity);

	getEntity().requireComponent(RigidBody.class);
	getEntity().requireComponent(Cannon.class);
	getEntity().collisionEvent.subscribe((x)->collisionEvent(x));
    }

    @Override
    public void start() {
	super.start();

	body = getComponent(RigidBody.class);
	cannon = getComponent(Cannon.class);

    }

    @Override
    public void update(final float deltaTime) {

	handleInput(deltaTime);
    }

    private void collisionEvent(CollisionComponent other){

    }

    protected void lookAt(Vector2 point){

        getEntity().getTransform().setRotation((float)(Math.atan2(point.y - getTransform().getPosition().y, point.x - getTransform().getPosition().x) * 180/Math.PI));
    }

    private  void handleInput(float deltaTime){

        lookAt(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));

        direction = new Vector2(0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){

            direction.y = 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.S)){

            direction.y = -1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){

            direction.x = -1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){

            direction.x = 1;
        }

	if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

	    cannon.fire();
	}

	accelerate(direction.nor(), deltaTime);
    }

    private void accelerate(Vector2 direction, float deltaTime){

	body.addVelocity(new Vector2(ACCELERATION * direction.x * deltaTime, ACCELERATION * direction.y * deltaTime));
	body.limitVelocity(MAX_VELOCITY);
    }
}

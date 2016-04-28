package com.mygdx.game.component.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.events.Event;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.factory.ParticleFactory;

public class PlayerController extends Behaviour
{

    private static final float ACCELERATION = 2000.0f;
    private static final float MAX_VELOCITY = 600.0f;

    public Event<Transform> playerDeathEvent;

    private RigidBody body = null;
    private BeamCannon cannon = null;

    public PlayerController(final Entity entity) {
	super(entity);

	playerDeathEvent = new Event<>();

	getEntity().requireComponent(RigidBody.class);
	getEntity().requireComponent(BeamCannon.class);
	getEntity().collisionEvent.subscribe(this::collisionEvent);
    }

    @Override
    public void start() {
	super.start();

	body = getComponent(RigidBody.class);
	cannon = getComponent(BeamCannon.class);


    }

    @Override
    public void update(final float deltaTime) {

	handleInput(deltaTime);
    }

    private void collisionEvent(CollisionComponent other){

	playerDeathEvent.notify(getTransform());

	ParticleFactory.circularDeathParticle(getTransform().getPosition(), Color.ORANGE, 500);

	CameraEffects.cameraShake(20.0f, 1.5f);
	getEntity().destroy();
    }

    private  void handleInput(float deltaTime){

        getTransform().lookAt(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));

	final Vector2 direction = new Vector2(0, 0);

        if(Gdx.input.isKeyPressed(Keys.W)){

	    direction.y = 1;
        }else if(Gdx.input.isKeyPressed(Keys.S)){

	    direction.y = -1;
        }

        if(Gdx.input.isKeyPressed(Keys.A)){

	    direction.x = -1;
        }else if(Gdx.input.isKeyPressed(Keys.D)){

	    direction.x = 1;
        }

	if(Gdx.input.isButtonPressed(Buttons.LEFT)){
	    cannon.fire();
	}

	accelerate(direction.nor(), deltaTime);
    }

    private void accelerate(Vector2 direction, float deltaTime){

	body.addVelocity(new Vector2(ACCELERATION * direction.x * deltaTime, ACCELERATION * direction.y * deltaTime));
	body.limitVelocity(MAX_VELOCITY);
    }
}

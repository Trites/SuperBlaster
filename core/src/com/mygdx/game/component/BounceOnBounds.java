package com.mygdx.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class BounceOnBounds extends Behaviour
{
    RigidBody body;
    float padding;

    public BounceOnBounds(final Entity entity) {
	super(entity);

	getEntity().requireComponent(RigidBody.class);
	padding = 0;
    }

    @Override
    public void start() {
	super.start();
	body = getComponent(RigidBody.class);

	CircleCollider circleCollider = getComponent(CircleCollider.class);

	if(circleCollider != null){

	    padding = circleCollider.getRadius();
	}
    }

    @Override
    public void update(final float deltaTime) {

	if(getTransform().getX() - padding < 0){

	    body.setVelocity(new Vector2(-body.getVelocity().x, body.getVelocity().y));
	    getTransform().setPosition(new Vector2(padding, getTransform().getY()));

	}else if(getTransform().getX() + padding > Gdx.graphics.getWidth()){

	    body.setVelocity(new Vector2(-body.getVelocity().x, body.getVelocity().y));
	    getTransform().setPosition(new Vector2(Gdx.graphics.getWidth() - padding, getTransform().getY()));
	}

	if(getTransform().getY() - padding < 0){

	    body.setVelocity(new Vector2(body.getVelocity().x, -body.getVelocity().y));
	    getTransform().setPosition(new Vector2(getTransform().getX(), padding));

	}else if(getTransform().getY() + padding > Gdx.graphics.getHeight()){

	    body.setVelocity(new Vector2(body.getVelocity().x, -body.getVelocity().y));
	    getTransform().setPosition(new Vector2(getTransform().getX(), Gdx.graphics.getHeight() - padding));
	}
    }
}

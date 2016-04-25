package com.mygdx.game.component.screenlimits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class ClampToScreen extends Behaviour
{
    RigidBody body;
    float padding;

    public ClampToScreen(final Entity entity) {
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

	Vector2 newPos = new Vector2(getTransform().getPosition()).mulAdd(body.getVelocity(), deltaTime);

	if(newPos.x - padding < 0){

	    body.setVelocity(new Vector2(0, body.getVelocity().y));
	    getTransform().setPosition(new Vector2(padding, getTransform().getY()));

	}else if(newPos.x + padding > Gdx.graphics.getWidth()){

	    body.setVelocity(new Vector2(0, body.getVelocity().y));
	    getTransform().setPosition(new Vector2(Gdx.graphics.getWidth() - padding, getTransform().getY()));
	}

	if(newPos.y - padding < 100){

	    body.setVelocity(new Vector2(body.getVelocity().x, 0));
	    getTransform().setPosition(new Vector2(getTransform().getX(), 100+ padding));

	}else if(newPos.y + padding > Gdx.graphics.getHeight()){

	    body.setVelocity(new Vector2(body.getVelocity().x, 0));
	    getTransform().setPosition(new Vector2(getTransform().getX(), Gdx.graphics.getHeight() - padding));
	}
    }
}

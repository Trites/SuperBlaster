package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;

public class SpawnAnimation extends Behaviour
{

    private SpriteComponent spriteComponent = null;
    private float spawnTimer;
    private Color oc = null;

    public SpawnAnimation(final Entity entity, final float spawnDelay) {
	super(entity);

	getEntity().requireComponent(SpriteComponent.class);
	spawnTimer = spawnDelay;
    }

    @Override
    public void start() {
	super.start();
	spriteComponent = getComponent(SpriteComponent.class);
	oc = spriteComponent.getColor();


    }

    @Override
    public void update(final float deltaTime) {

	spawnTimer -= deltaTime;
	spriteComponent.setColor(new Color(oc.r, oc.g, oc.b, (float) Math.random()));

	if(spawnTimer < 0){

	    spriteComponent.setColor(oc);
	    getComponent(RigidBody.class).setActive(true);
	    getComponent(RigidBody.class).setVelocity(new Vector2(0,0));
	    getComponent(CircleCollider.class).setActive(true);
	    destroy();
	}else{

	    getComponent(RigidBody.class).setActive(false);
	    getComponent(CircleCollider.class).setActive(false);
	}

    }
}

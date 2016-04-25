package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.game.component.controller.FollowController;

public class SpawnAnimation extends Behaviour
{
    private static float SPAWN_DELAY = 2f;

    private SpriteComponent spriteComponent;
    private float spawnTimer;
    private Color oc;

    public SpawnAnimation(final Entity entity) {
	super(entity);

	getEntity().requireComponent(SpriteComponent.class);
	spawnTimer = SPAWN_DELAY;
    }

    @Override
    public void start() {
	super.start();
	spriteComponent = getComponent(SpriteComponent.class);
	oc = spriteComponent.getColor();

	getComponent(FollowController.class).setActive(false);
	getComponent(CircleCollider.class).setActive(false);
    }

    @Override
    public void update(final float deltaTime) {

	spawnTimer -= deltaTime;
	spriteComponent.setColor(new Color(oc.r, oc.g, oc.b, (float) Math.random()));

	if(spawnTimer < 0){

	    spriteComponent.setColor(oc);
	    getComponent(FollowController.class).setActive(true);
	    getComponent(CircleCollider.class).setActive(true);
	    destroy();
	}

    }
}

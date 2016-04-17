package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;

public class SpawnEnemy extends Behaviour
{
    private static float SPAWN_DELAY = 2f;

    private SpriteComponent spriteComponent;
    private float spawnTimer;
    private Color oc;

    public SpawnEnemy(final Entity entity) {
	super(entity);

	getEntity().requireComponent(SpriteComponent.class);
	spawnTimer = SPAWN_DELAY;
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
	    getEntity().addComponent(new FollowController(getEntity(), "Player"));
	    destroy();
	}

    }
}

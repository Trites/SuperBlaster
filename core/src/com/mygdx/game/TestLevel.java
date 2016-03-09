package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.RenderComponentManager;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;

public class TestLevel extends PlayState
{
    private RenderComponentManager renderManager;
    private Entity entity;

    public TestLevel(final GameStateHandler handler) {
	super(handler);
    }

    @Override
    public void create() {

	super.create();


	renderManager = new RenderComponentManager();



	entity = new Entity(new Vector2(10, 10), new Vector2(1, 1), 0);

	SpriteComponent sp = new SpriteComponent(entity, 0, Vector2.Zero, new Vector2(1, 1), 0, new Sprite(new Texture("badlogic.jpg")));

	renderManager.add(sp);
	entity.addComponent(sp);
    }

    @Override
    public void update(final float deltaTime) {

	super.update(deltaTime);
	entity.update(deltaTime);
    }

    @Override
    public void render(final SpriteBatch batch) {

	super.render(batch);
	renderManager.render(batch);
    }
}

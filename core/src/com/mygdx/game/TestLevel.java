package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.CollisionComponentManager;
import com.mygdx.engine.entity.managers.RenderComponentManager;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;

public class TestLevel extends PlayState
{
    private CollisionComponentManager collisionManager;
    private RenderComponentManager renderManager;
    private Entity entity;
    private Entity entity2;

    private ShapeRenderer debugRender;

    public TestLevel(final GameStateHandler handler) {
	super(handler);
    }

    @Override
    public void create() {

	super.create();

	debugRender = new ShapeRenderer();

	byte[] collisionMap = {

		Byte.MAX_VALUE,
		Byte.MAX_VALUE,
		0,
		0,
		0,
		0,
		0,
		0
	};

	renderManager = new RenderComponentManager();
	collisionManager = new CollisionComponentManager(collisionMap);



	entity = new Entity(new Vector2(32, 32), new Vector2(1f, 1f), 0);
	SpriteComponent sp1 = new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png"));
	CircleCollider cc1 = new CircleCollider(entity, 30f, (byte)0);

	renderManager.add(sp1);
	collisionManager.add(cc1);
	entity.addComponent(sp1);
	entity.addComponent(cc1);
	entity.addComponent(new ControllerComponent(entity));

	entity2 = new Entity(new Vector2(300, 300), new Vector2(1f, 1f), 0);
	SpriteComponent sp2 = new SpriteComponent(entity2, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png"));
	CircleCollider cc2 = new CircleCollider(entity2, 30f, (byte)0);

	renderManager.add(sp2);
	collisionManager.add(cc2);
	entity2.addComponent(sp2);
	entity2.addComponent(cc2);
    }

    @Override
    public void update(final float deltaTime) {

	super.update(deltaTime);
	entity.update(deltaTime);
	entity2.update(deltaTime);

	collisionManager.update();

    }

    @Override
    public void render(final SpriteBatch batch) {

	super.render(batch);

	renderManager.render(batch);
	batch.end();

	collisionManager.debugRender(debugRender);

    }
}

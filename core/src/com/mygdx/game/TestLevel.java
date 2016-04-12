package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.EntityManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.RigidBodyManager;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;
import com.mygdx.game.EntityBlueprints.PlayerEntity;

public class TestLevel extends PlayState
{
    private CollisionManager collisionManager;
    private RenderManager renderManager;
    private RigidBodyManager bodyManager;

    private Entity entity;
    private Entity entity2;

    private ShapeRenderer debugRender;

    private EntityManager entityManager;

    World world;

    public TestLevel(final GameStateHandler handler) {
	super(handler);
    }

    @Override
    public void create() {

	super.create();

	debugRender = new ShapeRenderer();

	byte[] collisionMap = {

		3,
		Byte.MAX_VALUE,
		0,
		0,
		0,
		0,
		0,
		0
	};

	world = new World(collisionMap);
	entity = new Entity(world, new Vector2(60,60), new Vector2(1,1), 0f);
	entity.addComponent(new RigidBody(entity, 10f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0,0), new Vector2(1,1), 0, new Texture("Player.png")));
	entity.addComponent(new ControllerComponent(entity));

	world.start();
    }

    @Override
    public void update(final float deltaTime) {

	super.update(deltaTime);
	world.update(deltaTime);

    }

    @Override
    public void render(final SpriteBatch batch) {

	super.render(batch);
	world.render(batch);


	batch.end();
    }
}

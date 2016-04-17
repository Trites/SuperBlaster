package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.EntityManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.RigidBodyManager;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;
import com.mygdx.game.factory.EntityFactory;

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
    ParticleSystem particleSystem;

    public TestLevel(final GameStateHandler handler) {
	super(handler);
    }

    @Override
    public void create() {

	super.create();

	debugRender = new ShapeRenderer();

	byte[] collisionMap = {

		2,
		5,
		2,
		0,
		0,
		0,
		0,
		0
	};

	world = new World(collisionMap);
	particleSystem = ParticleSystem.GetInstance();
	particleSystem.addTexture("Plasma.png");
	//particleSystem.spawn(new Vector2(0,0),  (x, y, z)->FragmentParticle.update(x, y, z));
	//particleSystem.add(new Vector2(0, 0), );
	//particleSystem.add(new Vector2(150, 50));
	//particleSystem.add(new Vector2(250, 50));

	EntityFactory.BuildPlayer(world, new Transform(new Vector2(50,50), new Vector2(1,1), 0));
	EntityFactory.BuildFollower(world, new Transform(new Vector2(300,300), new Vector2(1,1), 0));

	//world.start();
    }

    private static float SPAWN_DELAY = 1f;
    private float spawnTimer = 0;

    @Override
    public void update(final float deltaTime) {

	super.update(deltaTime);
	world.update(deltaTime);
	particleSystem.update(deltaTime);

	spawnTimer -= deltaTime;

	if(spawnTimer < 0){

	    spawnTimer = SPAWN_DELAY;
	    float maxW = Gdx.graphics.getWidth() - 100;
	    float maxH = Gdx.graphics.getHeight() - 100;

	    world.queueAdd(EntityFactory.BuildFollower(world, new Transform(new Vector2((float) Math.random()*maxW + 100, (float) Math.random()*maxH + 100))));
	}
    }

    @Override
    public void render(final SpriteBatch batch) {

	super.render(batch);
	world.render(batch);
	particleSystem.render(batch);
	batch.end();

	//world.debugRender(debugRender);
    }
}

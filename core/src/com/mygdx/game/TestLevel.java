package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.factory.EntityFactory;

public class TestLevel extends PlayState
{

    private ShapeRenderer debugRender;

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

		2, // 0100 0000 (Player)->(Enemy)
		5, // 1010 0000 (Enemy)->(Player, Projectile)
		2, // 0100 0000 (Projectile)->(Enemy)
		0,
		0,
		0,
		0,
		0
	};

	world = new World(collisionMap);
	particleSystem = ParticleSystem.GetInstance();
	particleSystem.addTexture("Plasma.png");

	EntityFactory.BuildPlayerShip(world, new Transform(new Vector2(50,50), new Vector2(1,1), 0));
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

	CameraEffects.update(deltaTime);

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

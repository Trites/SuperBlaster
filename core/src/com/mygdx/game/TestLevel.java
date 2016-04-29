package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.RigidBodyManager;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.states.GameStateHandler;
import com.mygdx.engine.states.PlayState;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.component.controller.PlayerController;
import com.mygdx.game.factory.EntityBlueprints;

/**
 * Basic demo of the engines capabilities in the form of a bullet-hell space game.
 */
public class TestLevel extends PlayState
{

    private static final float RESET_TIME = 3.0f;

    private ShapeRenderer debugRender = null;

    World<CollisionManager, RenderManager> world = null;
    ParticleSystem particleSystem = null;

    float resetTimer;
    boolean reset;

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

	particleSystem = ParticleSystem.getInstance();
	particleSystem.addTexture("Plasma.png");

	world = new World(new CollisionManager(collisionMap), new RigidBodyManager(), new RenderManager());
	buildLevel();
    }

    private void resetLevel(Transform transform){

	System.out.println("Reset...");
	world.clear();
	reset = true;
	resetTimer = RESET_TIME;

	System.out.println("Done");
    }

    private void buildLevel(){

	System.out.println("New world...");
	//noinspection MagicNumber,MagicNumber
	EntityBlueprints.instantiatePlayer(world, new Transform(new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() /
													    2.0f), new Vector2(1, 1), 0));
	EntityBlueprints.instantiateFollowerSpawner(world, new Transform(new Vector2(0,0)));
	EntityBlueprints.instantiateStarFragmentSpawner(world, new Transform(new Vector2(0,0)));
	world.findEntity("Player").get(0).getComponent(PlayerController.class).playerDeathEvent.subscribe(this::resetLevel);
	System.out.println("Done");
	reset = false;
    }

    @Override
    public void update(final float deltaTime) {

	super.update(deltaTime);


	world.update(deltaTime);
	particleSystem.update(deltaTime);

	if(reset){

	    resetTimer -= deltaTime;

	    if(resetTimer <= 0) {
		buildLevel();
	    }
	}

	CameraEffects.update(deltaTime);

    }

    @Override
    public void render(final SpriteBatch batch) {
	super.render(batch);


	world.render(batch);
	particleSystem.render(batch);
	batch.end();

	//sworld.renderCollisionComponents(debugRender);
    }
}

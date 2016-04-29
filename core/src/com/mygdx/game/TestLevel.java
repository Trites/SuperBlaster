package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.RigidBodyManager;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.state.GameState;
import com.mygdx.engine.state.GameStateHandler;
import com.mygdx.engine.util.CameraEffects;
import com.mygdx.game.entities.component.controller.player.PlayerController;
import com.mygdx.game.entities.EntityBlueprints;

/**
 * Basic demo of the engines capabilities in the form of a bullet-hell space game.
 */
public class TestLevel extends GameState
{
    private static final float RESET_TIME = 3.0f;

    private World<CollisionManager, RenderManager> world = null;
    private ParticleSystem particleSystem = null;

    private float resetTimer;
    private boolean reset;

    public TestLevel(final GameStateHandler handler) {
	super(handler, false);
    }

    @Override
    public void create() {

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

	world = new World<>(new CollisionManager(collisionMap), new RigidBodyManager(), new RenderManager());
	buildLevel();
    }

    @SuppressWarnings("UnusedParameters") private void resetLevel(Transform transform){

	world.clear();
	reset = true;
	resetTimer = RESET_TIME;
    }

    private void buildLevel(){

	//noinspection MagicNumber,MagicNumber
	EntityBlueprints.instantiatePlayer(world, new Transform(new Vector2(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() /
													    2.0f), new Vector2(1, 1), 0));
	EntityBlueprints.instantiateFollowerSpawner(world, new Transform(new Vector2(0,0)));
	EntityBlueprints.instantiateStarFragmentSpawner(world, new Transform(new Vector2(0,0)));
	//noinspection ConstantConditions
	world.findEntity("Player").get(0).getComponent(PlayerController.class).playerDeathEvent.subscribe(this::resetLevel);
	reset = false;
    }

    @Override
    public void update(final float deltaTime) {

	world.update(deltaTime);
	particleSystem.update(deltaTime);

	if(reset){

	    resetTimer -= deltaTime;

	    if(resetTimer <= 0) {
		buildLevel();
	    }
	}

	CameraEffects.update(deltaTime);
	handleInput();
    }

    private void handleInput(){

	if(Gdx.input.isKeyPressed(Keys.ESCAPE)){

	    exit();
	}
    }

    @Override
    public void render(final SpriteBatch batch) {

	world.render(batch);
	particleSystem.render(batch);

    }

    @Override
    public void debugRender() {

	world.renderCollisionComponents(debugRender);
    }
}

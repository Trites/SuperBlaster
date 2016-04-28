package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.states.*;
import com.mygdx.engine.util.CameraEffects;

/**
 * Handles game loop
 */
public class MainGame extends ApplicationAdapter {

    /**
     * Time step size.
     */
    public static final float STEP = 1 / 60.0f;
    private float accumulatedDelta = 0;


    	private OrthographicCamera camera = null;
   	private SpriteBatch batch = null;

   	private GameStateHandler gameState = null;

   	@Override
   	public void create () {
   		batch = new SpriteBatch();

   		gameState = new GameStateHandler();

	    	gameState.pushState(new TestLevel(gameState));
   		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

	    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.setToOrtho(false);
	    camera.translate(0.0f, 0.0f);
	    camera.update();
	    CameraEffects.setCamera(camera);
   	}


   	@Override
   	public void render () {
   		accumulatedDelta += Gdx.graphics.getDeltaTime();

   		while (accumulatedDelta >= STEP){
   			accumulatedDelta -= STEP;
   			gameState.update(Gdx.graphics.getDeltaTime());

   			Gdx.gl.glClearColor(0, 0, 0, 1);
   			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		    	camera.update();
		   	batch.setProjectionMatrix(camera.combined);
   			batch.begin();
   			gameState.render(batch);

   		}


   	}
}

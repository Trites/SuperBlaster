package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.states.*;

/**
 * Handles game loop
 */
public class MainGame extends ApplicationAdapter {
    public static final float STEP = 1 / 60.0f;
   	private float accumulatedDelta = 0;

   	private SpriteBatch batch;

   	private GameStateHandler gameState;

   	@Override
   	public void create () {
   		batch = new SpriteBatch();
   		gameState = new GameStateHandler();

	    	gameState.pushState(new TestLevel(gameState));

   		System.out.println(batch.getBlendSrcFunc());
   		System.out.println(batch.getBlendDstFunc());
   		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
   		System.out.println(batch.getBlendSrcFunc());
   		System.out.println(batch.getBlendDstFunc());
   	}


   	@Override
   	public void render () {
   		accumulatedDelta += Gdx.graphics.getDeltaTime();

   		while (accumulatedDelta >= STEP){
   			accumulatedDelta -= STEP;
   			gameState.update(Gdx.graphics.getDeltaTime());

   			Gdx.gl.glClearColor(0, 0, 0, 1);
   			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

   			batch.begin();
   			gameState.render(batch);

   		}


   	}
}

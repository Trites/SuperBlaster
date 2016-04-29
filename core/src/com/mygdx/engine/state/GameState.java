package com.mygdx.engine.state;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * A GameState that can be handled by GameStateHandler.
 */
public abstract class GameState {

    private boolean debug;
    private GameStateHandler handler;
    protected ShapeRenderer debugRender = null;

    protected GameState(GameStateHandler handler, final boolean debug){

	this.debug = debug;
	this.handler = handler;

	if(debug){

	    debugRender = new ShapeRenderer();
	}
    }

    protected void exit(){

	handler.popState(this);
    }

    public abstract void create();
    public abstract void update(final float deltaTime);
    public abstract void render(SpriteBatch batch);
    public abstract void debugRender();

    public boolean isDebug() {
        return debug;
    }
}

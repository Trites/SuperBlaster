package com.mygdx.engine.states;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A GameState that can be handled by GameStateHandler.
 */
public abstract class GameState {

    private GameStateHandler handler;

    protected GameState(GameStateHandler handler){

	this.handler = handler;
    }

    protected void exit(){

	handler.popState(this);
    }

    public abstract void create();
    public abstract void update(final float deltaTime);
    public abstract void render(SpriteBatch batch);
}

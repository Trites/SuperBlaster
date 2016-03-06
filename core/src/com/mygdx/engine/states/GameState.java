package com.mygdx.engine.states;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {

    private GameStateHandler handler;

    public GameState(GameStateHandler handler){

	this.handler = handler;
    }

    protected void exit(){

	handler.popState(this);
    }

    public abstract void create();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);
}

package com.mygdx.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Handles different gamestates, like menues and actual gameplay.
 */
public class GameStateHandler
{

    private Stack<GameState> stateStack;

    public GameStateHandler(){

	stateStack = new Stack<>();
    }

    public void pushState(GameState state){

	state.create();
	stateStack.push(state);
    }

    public void popState(){

	stateStack.pop();
    }

    public void popState(GameState state){

	//noinspection ObjectEquality
	if(stateStack.peek() == state)
	    popState();
    }

    public void update(final float deltaTime){

	if(!stateStack.empty()){

	    stateStack.peek().update(deltaTime);
	}else{

	    Gdx.app.exit();
	}
    }

    public void render(SpriteBatch batch){


	if(!stateStack.empty()){

	    batch.begin();
	    stateStack.peek().render(batch);
	    batch.end();

	    if(stateStack.peek().isDebug())
		stateStack.peek().debugRender();
	}
    }
}

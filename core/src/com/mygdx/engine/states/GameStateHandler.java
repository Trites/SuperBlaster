package com.mygdx.engine.states;

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

	if(stateStack.peek() == state)
	    stateStack.pop();
    }

    public void update(final float deltaTime){

	if(!stateStack.empty())
	    stateStack.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch){

	if(!stateStack.empty())
	    stateStack.peek().render(batch);
    }
}

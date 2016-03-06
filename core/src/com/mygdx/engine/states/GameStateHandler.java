package com.mygdx.engine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateHandler
{

    private Stack<GameState> stateStack;

    public GameStateHandler(){

    }

    public void pushState(GameState state){

	stateStack.push(state);
    }

    public void popState(){

	stateStack.pop();
    }

    public void popState(GameState state){

	if(stateStack.peek() == state)
	    stateStack.pop();
    }

    public void update(float deltaTime){

	if(!stateStack.empty())
	    stateStack.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch){

	if(!stateStack.empty())
	    stateStack.peek().render(batch);
    }
}

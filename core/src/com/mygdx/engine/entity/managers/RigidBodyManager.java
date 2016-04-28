package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.defaultcomponents.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class RigidBodyManager extends Manager<RigidBody>
{
    private List<RigidBody> bodies;

    public RigidBodyManager() {

	bodies = new ArrayList<>();
    }

    @Override
    public void update(float deltaTime){

	super.update(deltaTime);

	for(RigidBody body : bodies){

	    if(body.isActive()){

		body.update(deltaTime);
	    }
	}
    }

    @Override
    public void add(final RigidBody element){

	bodies.add(element);
    }

    @Override
    public void remove(RigidBody element){

	bodies.remove(element);
    }

    @Override public void clear() {
	super.clear();
    	bodies.clear();
    }
}

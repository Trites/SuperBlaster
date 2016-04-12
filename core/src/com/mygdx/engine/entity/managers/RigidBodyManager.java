package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.defaultcomponents.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class RigidBodyManager implements Manager<RigidBody>
{
    private List<RigidBody> bodies;

    public RigidBodyManager() {

	bodies = new ArrayList<>();
    }

    public void update(float deltaTime){

	for(RigidBody body : bodies){

	    if(body.isActive()){

		body.update(deltaTime);
	    }
	}
    }

    @Override
    public void add(final RigidBody body){

	bodies.add(body);
    }

    @Override
    public void add(final List<RigidBody> bodies) {

	bodies.addAll(bodies);
    }

    @Override
    public void remove(RigidBody body){

	bodies.remove(body);
    }
}

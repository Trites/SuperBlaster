package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.defaultcomponents.RigidBody;

import java.util.ArrayList;
import java.util.List;

public class RigidBodyManager
{
    private List<RigidBody> bodies;

    public RigidBodyManager() {

	bodies = new ArrayList<>();
    }

    public void update(float deltaTime){

	for(RigidBody body : bodies)
	    body.update(deltaTime);
    }

    public void add(RigidBody body){

	bodies.add(body);
    }


    public void remove(RigidBody body){

	bodies.remove(body);
    }
}

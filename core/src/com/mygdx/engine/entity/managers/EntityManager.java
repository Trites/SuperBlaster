package com.mygdx.engine.entity.managers;
import com.mygdx.engine.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements Manager<Entity>
{
    private List<Entity> entities;

    public EntityManager() {

	entities = new ArrayList<>();
    }

    public void start(){

	for(Entity entity : entities)
	    entity.start();
    }

    public void update(final float deltaTime){

	for(Entity entity : entities)
	    entity.update(deltaTime);
    }

    @Override
    public void add(final Entity entity) {

	entities.add(entity);
    }

    @Override
    public void add(final List<Entity> entities) {

	for(final Entity entity : entities)
	    add(entity);
    }

    @Override
    public void remove(final Entity entity) {

	entities.remove(entity);
    }
}

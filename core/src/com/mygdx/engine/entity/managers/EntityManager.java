package com.mygdx.engine.entity.managers;
import com.mygdx.engine.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class EntityManager implements Manager<Entity>
{
    private List<Entity> entities;
    private HashMap<String, List<Entity>> tagMap;

    public EntityManager() {

	entities = new ArrayList<>();
	tagMap = new HashMap<>();
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
	addTag(entity, entity.getTag());
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

    public List<Entity> findEntity(final String tag){

	return tagMap.get(tag);
    }

    private void addTag(final Entity entity, final String tag){

	if(tag != ""){

	    if(tagMap.get(tag) == null)
	   	    tagMap.put(tag, new ArrayList<>());

	   	tagMap.get(tag).add(entity);
	}
    }

    public void updateTag(final Entity entity, final String oldTag, final String newTag){

	if(tagMap.get(oldTag) != null){

	    tagMap.get(oldTag).remove(entity);
	}

	addTag(entity, newTag);
    }
}

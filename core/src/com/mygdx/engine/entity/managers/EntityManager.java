package com.mygdx.engine.entity.managers;
import com.mygdx.engine.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages entities.
 */
public class EntityManager extends Manager<Entity>
{
    private List<Entity> entities;
    private Map<String, List<Entity>> tagMap;



    public EntityManager() {

	entities = new ArrayList<>();
	tagMap = new HashMap<>();
    }

    public void start(){

	//for(Entity entity : entities)
	//    entity.start();
    }

    public void update(final float deltaTime){

	super.update(deltaTime);

	for(Entity entity : entities){

	    if(entity.isActive())
	    	entity.update(deltaTime);
	}
    }

    @Override
    protected void add(final Entity element) {

	entities.add(element);
	addEntityTag(element, element.getTag());
	element.start();
    }

    @Override
    protected void remove(final Entity element) {

	removeEntityTag(element);
	entities.remove(element);
    }

    public List<Entity> findEntity(final String tag){

	return tagMap.get(tag);
    }

    private void addEntityTag(final Entity entity, final String tag){

	if(!tag.equals("")){

	    if(tagMap.get(tag) == null)
	   	    tagMap.put(tag, new ArrayList<>());

	   	tagMap.get(tag).add(entity);
	}
    }

    private void removeEntityTag(final Entity entity){

	if(tagMap.get(entity.getTag()) != null){

	    tagMap.get(entity.getTag()).remove(entity);
	}
    }

    public void updateTag(final Entity entity, final String oldTag){

	if(tagMap.get(oldTag) != null){

	    tagMap.get(oldTag).remove(entity);
	}

	addEntityTag(entity, entity.getTag());
    }

    @Override
    public void clear() {
	super.clear();
    	entities.clear();
	tagMap.clear();
    }
}

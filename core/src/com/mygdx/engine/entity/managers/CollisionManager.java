package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.Renderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Managed the interaction between CollisionComponents. It makes use of 8 collision layers to optimize collision checks.
 * A map of which layers collides with each other is supplied as a byte array with 8 elements where each elements represents a layer.
 */
public class CollisionManager extends Manager<CollisionComponent> implements Renderable<ShapeRenderer>
{
    private static final int LAYER_COUNT = 8; //Number of collision layers, tied to bits in a byte

    private byte[] collisionMap;
    private List<List<CollisionComponent>> collisionLayers;


    public CollisionManager(byte[] collisionMap) {

	this.collisionMap = collisionMap;
	collisionLayers = new ArrayList<>(LAYER_COUNT);

	for(int i = 0; i < LAYER_COUNT; i++)
	    collisionLayers.add(new ArrayList<>());
    }

    @Override
    public void add(CollisionComponent element){

	collisionLayers.get(element.getCollisionLayer()).add(element);
    }

    @Override
    public void remove(CollisionComponent element){

	collisionLayers.get(element.getCollisionLayer()).remove(element);
    }

    @Override
    public void update(final float deltaTime){

	super.update(deltaTime);

	for(int i = 0; i < LAYER_COUNT; i++){

	    for(CollisionComponent component : collisionLayers.get(i)){

		if(component.isActive()){

		    for(int j = i; j < LAYER_COUNT; j++){

			if(((collisionMap[component.getCollisionLayer()] >> j) & 1) == 1){ //If component collides with layer j

				collisionCheck(component, collisionLayers.get(j));
			}
		    }
		}
	    }
	}
    }

    private void collisionCheck(final CollisionComponent component, final Iterable<CollisionComponent> others){

	for(CollisionComponent other : others ){

	    if(component.intersectVisit(other)){

  		component.getEntity().notifyCollision(other);
  		other.getEntity().notifyCollision(component);
	    }
	}
    }

    @Override
    public void clear() {
	super.clear();
    	collisionLayers.clear();

	for(int i = 0; i < LAYER_COUNT; i++)
	    collisionLayers.add(new ArrayList<>());
    }

    public void render(final ShapeRenderer renderer){

	renderer.setColor(Color.YELLOW);
	renderer.begin(ShapeType.Line);
	for(List<CollisionComponent> layer : collisionLayers){
	    for(CollisionComponent component : layer){

		component.render(renderer);
	    }
	}
	renderer.end();
    }
}

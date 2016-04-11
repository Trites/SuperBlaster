package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;

import java.util.ArrayList;
import java.util.List;

public class CollisionComponentManager
{
    static final int LAYER_COUNT = 8; //Number of collision layers, tied to bits in a byte

    byte[] collisionMap;
    List<List<CollisionComponent>> collisionLayers;


    public CollisionComponentManager(byte[] collisionMap) {

	this.collisionMap = collisionMap;
	collisionLayers = new ArrayList<>(LAYER_COUNT);

	for(int i = 0; i < LAYER_COUNT; i++)
	    collisionLayers.add(new ArrayList<>());
    }

    public void add(CollisionComponent element){

	collisionLayers.get(element.getCollisionLayer()).add(element);
    }

    public void remove(CollisionComponent element){

	collisionLayers.get(element.getCollisionLayer()).remove(element);
    }

    public void update(){

	for(List<CollisionComponent> layer : collisionLayers){
	    for(CollisionComponent component : layer){

		for(int i = 0; i < LAYER_COUNT; i++){

		    if(((collisionMap[component.getCollisionLayer()] >> i) & 1) == 1){


			for(CollisionComponent other : collisionLayers.get(i) ){

			    if(component.intersectVisit(other)){

				component.getEntity().notifyCollision(other);
			    }
			}
		    }
		}
	    }
	}
    }

    public void debugRender(ShapeRenderer renderer){

	renderer.setColor(Color.YELLOW);
	renderer.begin(ShapeRenderer.ShapeType.Line);
	for(List<CollisionComponent> layer : collisionLayers){
	    for(CollisionComponent component : layer){

		renderer.circle(component.getTransform().getX(), component.getTransform().getY(), 30f);
	    }
	}
	renderer.end();
    }
}

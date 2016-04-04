package com.mygdx.engine.entity.managers;

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
	collisionLayers = new ArrayList<List<CollisionComponent>>(LAYER_COUNT);

	for(int i = 0; i < LAYER_COUNT; i++)
	    collisionLayers.add(new ArrayList<CollisionComponent>());
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

			    component.intersectVisit(other);
			}
		    }
		}
	    }
	}
    }
}

package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entity
{
    public Event<CollisionComponent> collisionEvent;

    private Transform transform;
    private HashMap<Class<? extends Component> , ArrayList<Component>> componentMap;	//Hashmap used for fast lookup of what components exists in the entity
    private List<Component> components;	//ArrayList used for iterating through the components during update, ArrayList is faster for iteration that the valueset of the HashMap

    public Entity(Vector2 position, Vector2 scale, float rotation) {

	collisionEvent = new Event<CollisionComponent>();
	componentMap = new HashMap<Class<? extends Component>, ArrayList<Component>>();
	components = new ArrayList<Component>();
    	transform = new Transform(position, scale, rotation);
    }

    public void update(final float deltaTime){

	for(Component component : components){
	    component.update(deltaTime);
	}
    }

    public <T extends Component> void addComponent(T component){

	if(!hasComponent(component.getClass())){

	    componentMap.put(component.getClass(), new ArrayList<Component>());
	}

	componentMap.get(component.getClass()).add(component);
	components.add(component);
    }

    public void removeComponent(Component component){

	Class<? extends Component> componentType = component.getClass();

	if(hasComponent(componentType)){

	    componentMap.get(componentType).remove(component);

	    if(!componentMap.get(componentType).isEmpty()){

		componentMap.remove(componentType);
	    }
	}
    }

    public boolean hasComponent(Class<? extends Component> type){

	return componentMap.containsKey(type);
    }

    public <T extends Component> T getComponent(Class<T> type){

	if(hasComponent(type)){

	    return (T)(componentMap.get(type).get(0));
	}

	return null;
    }

    public <T extends Component> ArrayList<T> getComponents(Class<T> type){

	if(hasComponent(type)){
	    if(!componentMap.get(type).isEmpty())
		return (ArrayList<T>)componentMap.get(type);
	}

	return null;
    }

    public Transform getTransform() {
	return transform;
    }

    public void notifyCollision(CollisionComponent other){

	collisionEvent.notify(other);
    }
}

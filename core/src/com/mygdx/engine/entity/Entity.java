package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Entity
{
    private World world;

    public Event<CollisionComponent> collisionEvent;

    private Transform transform;
    private HashMap<Class<? extends Component> , ArrayList<Component>> componentMap;	//Hashmap used for fast lookup of what behaviours exists in the entity
    private List<Behaviour> behaviours;	//ArrayList used for iterating through the behaviours during update, ArrayList is faster for iteration that the valueset of the HashMap
    private HashSet<Class<? extends Component>> requieredComponents;

    public Entity(World world, Vector2 position, Vector2 scale, float rotation) {
	this(world, new Transform(position, scale, rotation));
    }


    public Entity(World world, final Transform transform){

	this.world = world;
	this.transform = transform;

	collisionEvent = new Event<>();
	componentMap = new HashMap<>();
	behaviours = new ArrayList<>();
	requieredComponents = new HashSet<>();
	world.add(this);
    }

    public void start(){

	if(hasComponents(new ArrayList<>(requieredComponents))){

	    for(List<Component> components : componentMap.values())
		for(Component component : components)
		    component.start();
	}else{

	    System.out.println("ERROR: Missing dependency!");
	}
    }

    public void update(final float deltaTime){

	for(Behaviour behaviour : behaviours){
	    if(behaviour.isActive()){

		behaviour.update(deltaTime);
	    }
	}
    }

    public <T extends Behaviour> void addComponent(T behaviour){

	behaviours.add(behaviour);
	registerComponent(behaviour);
    }

    public <T extends ManagedComponent> void addComponent(T component){

	component.register(world);
	registerComponent(component);
    }

    private <T extends Component> void registerComponent(T component){

    if(!hasComponent(component.getClass())){

   	    componentMap.put(component.getClass(), new ArrayList<>());
   	}

   	componentMap.get(component.getClass()).add(component);
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

    public boolean hasComponents(List<Class<? extends Component>> types){

	for(Class<? extends Component> type : types){

	    if(!hasComponent(type))
		return false;
	}

	return true;
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

    public <T extends Component> List<T> getComponents(Class<T> type){

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

    public void requireComponent(Class<? extends Component> type){

	requieredComponents.add(type);
    }
}

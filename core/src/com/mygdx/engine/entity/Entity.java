package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.managers.ComponentManager;
import com.mygdx.engine.entity.managers.Destroyable;
import com.mygdx.engine.entity.managers.Startable;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Entity implements Destroyable, Startable
{
    private World world;

    public Event<CollisionComponent> collisionEvent;

    private boolean active;
    private String tag;
    private Transform transform;
    private HashMap<Class<? extends Component> , ArrayList<Component>> componentMap;	//Hashmap used for fast lookup of what behaviours exists in the entity
    private List<Behaviour> behaviours;	//ArrayList used for iterating through the behaviours during update, ArrayList is faster for iteration than the valueset of the HashMap
    private HashSet<Class<? extends Component>> requieredComponents;

    public Entity(World world, Vector2 position, Vector2 scale, float rotation) {
	this(world, new Transform(position, scale, rotation));
    }


    public Entity(World world, final Transform transform){

	this.world = world;
	this.transform = transform;
	this.tag = "";
	this.active = false;

	collisionEvent = new Event<>();
	componentMap = new HashMap<>();
	behaviours = new ArrayList<>();
	requieredComponents = new HashSet<>();
	world.queueAdd(this);
    }

    @Override
    public void start(){

	/*System.out.println("KeySet:");
	System.out.println(componentMap.keySet());
	System.out.println("");*/

	if(hasComponents(new ArrayList<>(requieredComponents))){

	    for(List<Component> components : componentMap.values())
		for(Component component : components)
		    component.start();
	}else{

	    System.out.println("ERROR: Missing dependency!");
	}

	active = true;
    }

    public void update(final float deltaTime){


	for(int i = 0; i < behaviours.size(); i++){

	    Behaviour behaviour = behaviours.get(i);
	    if(behaviour.isActive()){

		behaviour.update(deltaTime);
	    }
	}

	/*for(Behaviour behaviour : behaviours){
	    if(behaviour.isActive()){

		behaviour.update(deltaTime);
	    }
	}*/
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

	if(active)
	    component.start();
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

    public <T extends Component> boolean hasComponent(Class<T> type){

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

    public List<Entity> findEntity(final String tag){

	return world.findEntity(tag);
    }

    public Transform getTransform() {
	return transform;
    }

    public void notifyCollision(CollisionComponent data){

	collisionEvent.notify(data);
    }

    public void requireComponent(Class<? extends Component> type){

	requieredComponents.add(type);
    }

    public String getTag() {
	return tag;
    }

    public void setTag(final String tag) {

	String oldTag = this.tag;
	this.tag = tag;
	world.updateTag(this, oldTag);

    }

    public World getWorld(){

	return world;
    }

    public boolean isActive() {
	return active;
    }

    public ComponentManager getComponentManager(){

	return world;
    }

    @Override
    public void destroy(){

	for(final List<Component> components : componentMap.values()){
	    for(final Component component : components){

		//component.setActive(false);
		component.destroy();
	    }
	}

	world.queueRemoval(this);
    }

    @Override
    public void destroyImmediate(){


	for(final List<Component> components : componentMap.values()){
	    for(final Component component : components){

		component.destroyImmediate();
	    }
	}
    }
}

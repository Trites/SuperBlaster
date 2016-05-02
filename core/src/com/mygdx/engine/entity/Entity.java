package com.mygdx.engine.entity;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.component.Component;
import com.mygdx.engine.entity.component.ManagedComponent;
import com.mygdx.engine.entity.component.defaultcomponent.CollisionComponent;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.ComponentManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.engine.event.Event;
import com.mygdx.engine.exception.DuplicateComponentException;
import com.mygdx.engine.exception.MissingDependencyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Base for entity composition. An Entity can contain several components, which defines its behaviour.
 */
public final class Entity implements Startable, Destroyable
{
    private World<CollisionManager, RenderManager> world;

    /**
     * Invoked when a CollisionComponent reports a collision.
     */
    public Event<CollisionComponent> collisionEvent;

    private boolean active;
    private String tag;
    private Transform transform;
    private Map<Class<? extends Component> , Component> componentMap;	//Hashmap used for fast lookup of what behaviours exists in the entity
    private List<Behaviour> behaviours;	//ArrayList used for iterating through the behaviours during update, ArrayList is faster for iteration than the valueset of the HashMap
    private HashSet<Class<? extends Component>> requieredComponents;

    public Entity(World<CollisionManager, RenderManager> world, final Transform transform){

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

    /**
     * Activates the Entity. Checks if there are any missing dependencies.
     */
    @Override
    public void start(){

	if(hasComponents(new ArrayList<>(requieredComponents))){

	    for(Component component : componentMap.values())
		component.start();

	}else{

	    new MissingDependencyException("Entity is missing a component that is requiered by another component.").printStackTrace();
	}

	active = true;
    }

    /**
     * Updates all behaviours belonging to this entity, removing dead behaviours.
     * @param deltaTime Delta time in miliseconds.
     */
    public void update(final float deltaTime){


	for(Iterator<Behaviour> iterator = behaviours.iterator(); iterator.hasNext();){

	    Behaviour behaviour = iterator.next();

	    if(behaviour.isActive())
	    	behaviour.update(deltaTime);

	    if(!behaviour.isAlive()){

		removeComponent(behaviour);
		iterator.remove();
	    }
 	}
    }

    /**
     * Adds the behaviour to this entity, activates it if the entity is already active.
     * @param behaviour The behaviour to be added.
     * @param <T> T extends Behaviour
     */
    public <T extends Behaviour> void addComponent(T behaviour){

	behaviours.add(behaviour);
	registerComponent(behaviour);
    }

    /**
     * Adds the ManagedComponent to this entity, activates it if the entity is already active.
     * @param component The ManagedComponent to be added.
     * @param <T> T extends ManagedComponent
     */
    public <T extends ManagedComponent> void addComponent(T component){

	component.register(world);
	registerComponent(component);
    }

    /**
     * Adds the component to the component map, checks for duplicate components.
     * @param component The component to be added.
     * @param <T> T extends Component.
     */
    private <T extends Component> void registerComponent(T component){

	if(!hasComponent(component.getClass())){

	    componentMap.put(component.getClass(), component);
	}else{

	    new DuplicateComponentException("Entity already contains a component of this type.").printStackTrace();
	}

	if(active)
	    component.start();
    }

    /**
     * Removes the given component.
     * @param component Component to be removed.
     */
    //Strong type needed for class check.
    public void removeComponent(Component component){

	Class<? extends Component> componentType = component.getClass();

	if(hasComponent(componentType)){

	    componentMap.remove(componentType);
	    component.dispose();
	}
    }

    /**
     * Checks if the entity has all components in the given collection.
     * @param types The collection of component types.
     * @return True if entity has all component types in the collection.
     */
    public boolean hasComponents(Iterable<Class<? extends Component>> types){

	for(Class<? extends Component> type : types){

	    if(!hasComponent(type))
		return false;
	}

	return true;
    }

    /**
     * Checks if the entity has the given component type.
     * @param type The type to check for.
     * @param <T> T extends Component.
     * @return True if entity has a component of the given type.
     */
    public <T extends Component> boolean hasComponent(Class<T> type){

	return componentMap.containsKey(type);
    }

    /**
     * Returns a reference to the component of the given type, if one is present in the entity.
     * @param type The type of component to get a reference to.
     * @param <T> T extgends Component
     * @return A reference to a component of the given type if one is present, otherwise null.
     */
    public <T extends Component> T getComponent(Class<T> type){

	if(hasComponent(type)){

	    //Type can be guarateed from the way it is added.
	    return (T)(componentMap.get(type));
	}

	return null;
    }

    /**
     * Looks for entities that has the given tag in the world that this entity belongs to.
     * Calls world.findEntity(tag)
     * @param tag The tag to look for
     * @return A collection with all entities with the given tag.
     */
    public List<Entity> findEntity(final String tag){

	return world.findEntity(tag);
    }

    /**
     *
     * @return Reference to the transform of this entity.
     */
    public Transform getTransform() {
	return transform;
    }

    /**
     * Invokes the collision event of this entity.
     * @param data Data to be passed to the event.
     */
    public void notifyCollision(CollisionComponent data){

	collisionEvent.notify(data);
    }

    /**
     * Adds the component type to the requieredComponent list.
     * This list is ckecked in the start method.
     * @param type The type to be requiered.
     */
    public void requireComponent(Class<? extends Component> type){

	requieredComponents.add(type);
    }

    /**
     *
     * @return The tag of this entity.
     */
    public final String getTag() {
	return tag;
    }

    /**
     * Sets the tag of this entity.
     * @param tag The new tag.
     */
    public void setTag(final String tag) {

	String oldTag = this.tag;
	this.tag = tag;
	world.updateTag(this, oldTag); //Update the tag dictionary in world.

    }

    /**
     *
     * @return Reference to the world that this Entity belongs to.
     */
    public World<CollisionManager, RenderManager> getWorld(){

	return world;
    }

    /**
     *
     * @return Reference to the ComponantManager that this Entity belongs to.
     */
    public ComponentManager getComponentManager(){

	return world;
    }

    public boolean isActive() {
	return active;
    }

    @Override
    public void destroy(){

	for(Component component : componentMap.values()){
	    component.setActive(false);
 	}

	world.queueRemoval(this);
    }

    @Override
    public void dispose(){

	for(final Component component : componentMap.values()){

	    component.dispose();
 	}
    }
}

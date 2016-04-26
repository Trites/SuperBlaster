package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.Renderable;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class World<T extends Manager<RenderComponent> & Renderable> extends EntityManager implements ComponentManager
{
    private Manager<CollisionComponent> collisionManager;
    private Manager<RigidBody> rigidBodyManager;
    private T renderManager;

    public World(Manager<CollisionComponent> collisionManager, Manager<RigidBody> rigidBodyManager, T renderManager) {

	this.collisionManager = collisionManager;
	this.rigidBodyManager = rigidBodyManager;
	this.renderManager = renderManager;
    }

    public void update(final float deltaTime){
	super.update(deltaTime);
	collisionManager.update(deltaTime);
	rigidBodyManager.update(deltaTime);
	renderManager.update(deltaTime);
    }

    public void render(SpriteBatch batch){

	renderManager.render(batch);
    }

    @Override
    public void registerComponent(final RigidBody component){

	rigidBodyManager.add(component);
    }

    @Override
    public void registerComponent(final CollisionComponent component){

	collisionManager.add(component);
    }

    @Override
    public void registerComponent(final RenderComponent component){

	renderManager.add(component);
    }

    @Override
    public void deregisterComponent(final RigidBody component) {

	rigidBodyManager.queueRemoval(component);
    }

    @Override
    public void deregisterComponent(final CollisionComponent component) {

	collisionManager.queueRemoval(component);
    }

    @Override
    public void deregisterComponent(final RenderComponent component) {

	renderManager.queueRemoval(component);
    }

    @Override public void clear() {
	super.clear();
    	collisionManager.clear();
	renderManager.clear();
	rigidBodyManager.clear();
    }
}

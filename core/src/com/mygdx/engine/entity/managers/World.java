package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.Renderable;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

/**
 * World contains all systems requiered to manage Entities.
 * It assumes three types of ManagedComponents, the managers for these can be injected by the user.
 * @param <C> CollisionManager
 * @param <R> RenderManager
 */
public class World<C extends Manager<CollisionComponent> & Renderable<ShapeRenderer>, R extends Manager<RenderComponent> & Renderable<SpriteBatch>>
	extends EntityManager implements ComponentManager
{
    private C collisionManager;
    private Manager<RigidBody> rigidBodyManager;
    private R renderManager;

    private boolean clear = false;

    public World(C collisionManager, Manager<RigidBody> rigidBodyManager, R renderManager) {

	this.collisionManager = collisionManager;
	this.rigidBodyManager = rigidBodyManager;
	this.renderManager = renderManager;
    }

    public void update(final float deltaTime){
	super.update(deltaTime);
	collisionManager.update(deltaTime);
	rigidBodyManager.update(deltaTime);
	renderManager.update(deltaTime);

	if(clear)
	    clearNow();
    }

    public void render(SpriteBatch batch){

	renderManager.render(batch);
    }

    public void renderCollisionComponents(final ShapeRenderer renderer){

	collisionManager.render(renderer);
    }

    @Override
    public void registerComponent(final RigidBody component){

	rigidBodyManager.queueAdd(component);
    }

    @Override
    public void registerComponent(final CollisionComponent component){

	collisionManager.queueAdd(component);
    }

    @Override
    public void registerComponent(final RenderComponent component){

	renderManager.queueAdd(component);
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

    @Override
    public void clear() {

	clear = true;
    }

    private void clearNow(){
	super.clear();
    	collisionManager.clear();
	renderManager.clear();
	rigidBodyManager.clear();
	clear = false;
    }
}

package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public class World extends EntityManager
{
    private CollisionManager collisionManager;
    private RigidBodyManager rigidBodyManager;

    private RenderManager renderManager;

    public World(byte[] collisionMap) {

	collisionManager = new CollisionManager(collisionMap);
	renderManager = new RenderManager();
	rigidBodyManager = new RigidBodyManager();
    }

    public void update(final float deltaTime){
	super.update(deltaTime);
	collisionManager.update();
	rigidBodyManager.update(deltaTime);
    }

    public void render(SpriteBatch batch){

	renderManager.render(batch);
    }

    public void debugRender(ShapeRenderer renderer){

	collisionManager.debugRender(renderer);
    }

    public void registerComponent(final RigidBody component){

	rigidBodyManager.add(component);
    }

    public void registerComponent(final CollisionComponent component){

	collisionManager.add(component);
    }

    public void registerComponent(final RenderComponent component){

	renderManager.add(component);
    }
}

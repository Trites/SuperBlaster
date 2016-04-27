package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.ManagedComponent;
import com.mygdx.engine.entity.managers.ComponentManager;
import com.mygdx.engine.entity.managers.World;

public abstract class CollisionComponent extends ManagedComponent implements CollisionVisitor, CollisionElement
{
    private int collisionLayer;

    protected Vector2 relativePosition;

    public CollisionComponent(final Entity entity, Vector2 relativePosition, final int collisionLayer) {
	super(entity);
	this.collisionLayer = collisionLayer;
	this.relativePosition = relativePosition;
    }

    public int getCollisionLayer() {
	return collisionLayer;
    }

    public void notifyCollision(CollisionComponent other){

	this.getEntity().notifyCollision(other);
    }

    @Override
    public void register(final ComponentManager world) {

	world.registerComponent(this);
    }

    @Override
    public void deregister(final ComponentManager world) {

	world.deregisterComponent(this);
    }

    public Vector2 getRelativePosition() {
	return relativePosition;
    }

    public void setRelativePosition(final Vector2 relativePosition) {
	this.relativePosition = relativePosition;
    }

    public abstract void render(final ShapeRenderer renderer);

}

package com.mygdx.engine.entity.component.defaultcomponent;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.component.ManagedComponent;
import com.mygdx.engine.entity.managers.ComponentManager;

/**
 * Base for a ManagedComponent that can collide with other components derived from this class.
 * Has support for collision layers.
 */
public abstract class CollisionComponent extends ManagedComponent implements CollisionVisitor, CollisionElement
{
    private int collisionLayer;

    protected CollisionComponent(final Entity entity, final int collisionLayer) {
	super(entity);
	this.collisionLayer = collisionLayer;
    }

    public int getCollisionLayer() {
	return collisionLayer;
    }

    @Override
    public void register(final ComponentManager world) {

	world.registerComponent(this);
    }

    @Override
    public void deregister(final ComponentManager world) {

	world.deregisterComponent(this);
    }

    public abstract void render(final ShapeRenderer renderer);

}

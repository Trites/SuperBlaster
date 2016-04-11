package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Component;
import com.mygdx.engine.entity.Entity;

public abstract class CollisionComponent extends Component implements CollisionVisitor, CollisionElement
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

    public Vector2 getRelativePosition() {
	return relativePosition;
    }

    public void setRelativePosition(final Vector2 relativePosition) {
	this.relativePosition = relativePosition;
    }
}

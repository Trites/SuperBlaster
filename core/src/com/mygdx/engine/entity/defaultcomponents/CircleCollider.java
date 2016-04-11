package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class CircleCollider extends CollisionComponent
{
    private float radius;

    public CircleCollider(final Entity entity, float radius, final byte collisionLayer) {
	this(entity, radius, new Vector2(0,0), collisionLayer);
    }

    public CircleCollider(final Entity entity, float radius, Vector2 relativePosition, final byte collisionLayer) {

	super(entity, relativePosition, collisionLayer);

	this.radius = radius;
    }


    @Override
    public boolean intersectVisit(final CollisionVisitor other) {

	if(other == this)
	    return false;

	return other.intersects(this);
    }

    @Override
    public boolean intersects(final CircleCollider other) {

	return this.radius + other.radius >= Vector2.dst(this.getTransform().getX() + this.relativePosition.x,
				   		this.getTransform().getY() + this.relativePosition.y,
				   		other.getTransform().getX() + other.relativePosition.x,
				   		other.getTransform().getY() + other.relativePosition.y);
    }
}

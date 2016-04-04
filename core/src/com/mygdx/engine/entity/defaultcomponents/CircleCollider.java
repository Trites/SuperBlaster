package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class CircleCollider extends CollisionComponent
{
    private float radius;

    public CircleCollider(float radius, final Entity entity, final byte collisionLayer) {

	super(entity, collisionLayer);

	this.radius = radius;
    }


    @Override
    public boolean intersectVisit(final CollisionVisitor other) {

	return other.intersects(this);
    }

    @Override
    public boolean intersects(final CircleCollider other) {

	return this.radius + other.radius >= Vector2.dst2(this.getTransform().getX() + this.relativePosition.x,
				   		this.getTransform().getY() + this.relativePosition.y,
				   		other.getTransform().getX() + other.relativePosition.x,
				   		other.getTransform().getY() + other.relativePosition.y);
    }
}

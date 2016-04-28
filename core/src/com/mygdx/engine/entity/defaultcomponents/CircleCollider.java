package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

/**
 * Derived from CollisionComponent. This class specifies the collision behaviour of a circle.
 */
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

	if(!this.isActive() || !other.isActive())
	    return false;

	return edgeDistance(other) <= 0;
    }

    @Override
    public float edgeDistance(final CircleCollider other){

	return Vector2.dst(this.getTransform().getX() + this.relativePosition.x,
			   this.getTransform().getY() + this.relativePosition.y,
			   other.getTransform().getX() + other.relativePosition.x,
			   other.getTransform().getY() + other.relativePosition.y) - (this.radius + other.radius);
    }

    public float getRadius() {
	return radius;
    }

    @Override
    public void render(final ShapeRenderer renderer) {

	renderer.circle(getTransform().getPosition().x, getTransform().getPosition().y, radius);
    }
}
